package ru.itis.ruzavin.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.ruzavin.dto.CreateUserDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.services.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@AllArgsConstructor
public class UserController {
	private UserService userService;

	@Operation(summary = "Create user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User was created",
					content = {
							@Content(mediaType = "text/html"
							)
					}
			)
	})
	@PostMapping("/signUp")
	public String createUser(@Valid @ModelAttribute(name = "user") CreateUserDTO form, Model model, HttpServletRequest request){
		String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
		Optional<UserDTO> userDTO = userService.saveUser(form, url);
		model.addAttribute("user", userDTO.get());
		return "signUpSuccess";
	}

	@Operation(summary = "Verify user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User was verified",
					content = {
							@Content(mediaType = "text/html"
							)
					}
			)
	})
	@GetMapping("/verification")
	public String verify(@Param("code") String code) {
		if (userService.verify(code)) {
			return "verification_success";
		} else {
			return "verification_failed";
		}
	}

}
