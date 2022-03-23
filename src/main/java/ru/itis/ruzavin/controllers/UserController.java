package ru.itis.ruzavin.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.ruzavin.dto.CreateUserDTO;
import ru.itis.ruzavin.dto.SignInDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.services.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@AllArgsConstructor
public class UserController {
	private UserService userService;

	@PostMapping("/signUp")
	public String createUser(@Valid @ModelAttribute(name = "user") CreateUserDTO form, Model model, HttpServletRequest request){
		String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
		Optional<UserDTO> userDTO = userService.saveUser(form, url);
		model.addAttribute("user", userDTO);
		return "signUpSuccess";
	}

	@GetMapping("/verification")
	public String verify(@Param("code") String code) {
		if (userService.verify(code)) {
			return "verification_success";
		} else {
			return "verification_failed";
		}
	}

	@GetMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> getAllUsers() {
		return getAllUsers();
	}

	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO getUserById(@PathVariable Integer id) {
		return userService.getUserById(id);
	}
}
