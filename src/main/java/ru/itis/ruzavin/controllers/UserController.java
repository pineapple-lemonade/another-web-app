package ru.itis.ruzavin.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ruzavin.dto.CreateUserDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.models.User;
import ru.itis.ruzavin.services.interfaces.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {
	private UserService userService;

	@PostMapping("/users")
	public UserDTO createUser(@Valid @RequestBody CreateUserDTO form){
		User user = User.builder()
				.email(form.getEmail())
				.password(form.getPassword())
				.name(form.getName())
				.build();
		Optional<UserDTO> userDTO = userService.saveUser(user);

		return userDTO.orElse(null);
	}
}
