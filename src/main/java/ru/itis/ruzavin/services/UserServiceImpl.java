package ru.itis.ruzavin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	@Transactional
	@Override
	public Optional<UserDTO> saveUser(User user) {
		if (userRepository.findByEmail(user.getEmail()) == null){
			user.setPassword(PasswordHelper.encrypt(user.getPassword()));
			userRepository.save(user);
			return Optional.of(UserDTO.fromModel(user));
		} else {
			return Optional.empty();
		}
	}
}
