package ru.itis.ruzavin.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.ruzavin.dto.CreateUserDTO;
import ru.itis.ruzavin.dto.SignInDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.services.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
}
