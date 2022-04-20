package ru.itis.ruzavin.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "Get appeals by user id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Appeals was get",
					content = {
							@Content(mediaType = "application/json"
							)
					}
			)
	})
	@GetMapping("/appeal/user/{id}")
	public List<AppealDTO> getAppealsByUserId(@PathVariable Integer id){
		return appealService.getAppealsByUserId(id);
	}

	@Operation(summary = "Get appeals by city")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Appeals was get",
					content = {
							@Content(mediaType = "application/json"
							)
					}
			)
	})
	@GetMapping("/appeal/city/{city}")
	public List<AppealDTO> getAppealsByCityId(@PathVariable String city){
		return appealService.getAppealsByCity(city);
	}
}
