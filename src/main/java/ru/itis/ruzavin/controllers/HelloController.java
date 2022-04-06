package ru.itis.ruzavin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.ruzavin.aspect.CountCallsOfMethod;
import ru.itis.ruzavin.dto.CreateUserDTO;

@Controller
public class HelloController {
	@GetMapping("")
	public String getIndexPage() {
		return "index";
	}

	@CountCallsOfMethod
	@GetMapping("/signUp")
	public String getSignUp(Model model) {
		model.addAttribute("user", new CreateUserDTO());
		return "signUp";
	}

	@GetMapping("/home")
	public String getHome() {
		return "home";
	}

}
