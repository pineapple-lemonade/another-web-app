package ru.itis.ruzavin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.ruzavin.dto.CreateUserDTO;

@Controller
public class HelloController {
	@GetMapping("")
	public String getIndexPage() {
		return "index";
	}

	@GetMapping("/signUp")
	public String getSignUp(Model model) {
		model.addAttribute("user", new CreateUserDTO());
		return "signUp";
	}

	@GetMapping("/home")
	public String getHome() {
		return "home";
	}

	@GetMapping("/signIn")
	public String getLoginPage(Model model) {
		model.addAttribute("user", new CreateUserDTO());
		return "signIn";
	}
}
