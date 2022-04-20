package ru.itis.ruzavin.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.ruzavin.aspect.CountCallsOfMethod;
import ru.itis.ruzavin.dto.CreateUserDTO;

@Controller
public class HelloController {
	@Operation(summary = "Get index page")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page was get",
					content = {
							@Content(mediaType = "text/html"
							)
					}
			)
	})
	@GetMapping("")
	public String getIndexPage() {
		return "index";
	}

	@CountCallsOfMethod
	@Operation(summary = "Get sign up page")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page was get",
					content = {
							@Content(mediaType = "text/html"
							)
					}
			)
	})
	@GetMapping("/signUp")
	public String getSignUp(Model model) {
		model.addAttribute("user", new CreateUserDTO());
		return "signUp";
	}

	@Operation(summary = "Get home page")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page was get",
					content = {
							@Content(mediaType = "text/html"
							)
					}
			)
	})
	@GetMapping("/home")
	public String getHome() {
		return "home";
	}

}
