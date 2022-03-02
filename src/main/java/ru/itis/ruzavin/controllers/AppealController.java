package ru.itis.ruzavin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ruzavin.dto.AppealDTO;
import ru.itis.ruzavin.services.interfaces.AppealService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppealController {
	private final AppealService appealService;

	@GetMapping("/appeal/user/{id}")
	public List<AppealDTO> getAppealsByUserId(@PathVariable Integer id){
		return appealService.getAppealsByUserId(id);
	}

	@GetMapping("/appeal/city/{city}")
	public List<AppealDTO> getAppealsByCityId(@PathVariable String city){
		return appealService.getAppealsByCity(city);
	}
}
