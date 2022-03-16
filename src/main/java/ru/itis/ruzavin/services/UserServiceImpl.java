package ru.itis.ruzavin.services;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.ruzavin.config.MailConfig;
import ru.itis.ruzavin.dto.CreateUserDTO;
import ru.itis.ruzavin.dto.SignInDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.helpers.PasswordHelper;
import ru.itis.ruzavin.models.User;
import ru.itis.ruzavin.repositories.UserRepository;
import ru.itis.ruzavin.services.interfaces.UserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	private final JavaMailSender javaMailSender;
	private final MailConfig mailConfig;

	@Transactional
	@Override
	public Optional<UserDTO> saveUser(CreateUserDTO form, String url) {
		if (userRepository.findByEmail(form.getEmail()) == null) {
			String code = RandomString.make(64);
			User user = User.builder()
					.email(form.getEmail())
					.name(form.getName())
					.verificationCode(code)
					.password(encoder.encode(form.getPassword()))
					.isEnable(false)
					.build();
			userRepository.save(user);

			new Thread(() -> sendVerificationMail(form.getEmail(), form.getName(), code, url)).start();

			return Optional.of(UserDTO.fromModel(user));
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	@Override
	public Optional<UserDTO> signIn(SignInDTO form) {
		User user = userRepository.findByEmail(form.getEmail());
		if (user != null) {
			if (encoder.encode(form.getPassword()).equals(user.getPassword())) {
				UserDTO userDTO = UserDTO.fromModel(user);
				return Optional.of(userDTO);
			}
		}
		return Optional.empty();
	}

	@Override
	public boolean verify(String code) {
		Optional<User> optionalUser = userRepository.findUserByVerificationCodeLike(code);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setVerificationCode(null);
			user.setEnable(true);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public void sendVerificationMail(String mail, String name, String code, String url) {
		String from = mailConfig.getFrom();
		String sender = mailConfig.getSender();
		String subject = mailConfig.getSubject();
		String content = mailConfig.getContent();

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

		try {
			helper.setFrom(from, sender);

			helper.setTo(mail);
			helper.setSubject(subject);

			content = content.replace("{name}", name);
			content = content.replace("{url}", url + "/verification?code=" + code);

			helper.setText(content, true);
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		javaMailSender.send(mimeMessage);
	}
}
