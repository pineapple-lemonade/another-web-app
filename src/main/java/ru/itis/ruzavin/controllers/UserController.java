package ru.itis.ruzavin.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ruzavin.dto.CreateUserDTO;
import ru.itis.ruzavin.dto.SignInDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.services.interfaces.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@Slf4j
@AllArgsConstructor
public class UserController {
	private UserService userService;

	@PostMapping("/signUp")
	public String createUser(@Valid CreateUserDTO form, Model model){
		Optional<UserDTO> userDTO = userService.saveUser(form);
		model.addAttribute("user", userDTO);
		return "signUpSuccess";
	}

	@PostMapping("/signIn")
	public String signIn(SignInDTO form) {
		Optional<UserDTO> userDTO = userService.signIn(form);
		if (userDTO.isPresent()) {
			return "signInSuccess";
		} else {
			return "signInFailed";
		}
	}
}
