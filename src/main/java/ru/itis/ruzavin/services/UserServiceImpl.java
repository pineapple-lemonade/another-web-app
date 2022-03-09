package ru.itis.ruzavin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.ruzavin.dto.CreateUserDTO;
import ru.itis.ruzavin.dto.SignInDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.helpers.PasswordHelper;
import ru.itis.ruzavin.models.User;
import ru.itis.ruzavin.repositories.UserRepository;
import ru.itis.ruzavin.services.interfaces.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;

	@Transactional
	@Override
	public Optional<UserDTO> saveUser(CreateUserDTO form) {
		if (userRepository.findByEmail(form.getEmail()) == null){
			User user = User.builder()
					.email(form.getEmail())
					.name(form.getName())
					.build();
			user.setPassword(encoder.encode(form.getPassword()));
			userRepository.save(user);
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
}
