package ru.itis.ruzavin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ruzavin.repository.UserRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class HelloController {

	private final UserRepository userRepository;

	@GetMapping("/hello")
	public String hello(@RequestParam Optional<String> name) {
		return String.format("hello, %s", name.orElse("name"));
	}
}
