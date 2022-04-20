package ru.itis.ruzavin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.ruzavin.dto.ForecastDTO;
import ru.itis.ruzavin.dto.ForecastFormDTO;
import ru.itis.ruzavin.helpers.JsonHelper;
import ru.itis.ruzavin.helpers.WeatherHelper;
import ru.itis.ruzavin.models.Forecast;
import ru.itis.ruzavin.services.interfaces.ForecastService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class WeatherController {

	private ForecastService forecastService;
	private JsonHelper jsonHelper;


	@Operation(summary = "Create weather")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Weather created",
					content = {
							@Content(mediaType = "application/json",
									schema =
									@Schema(implementation = ForecastDTO.class)
							)
					}
			)
	})
	@PostMapping("/weather")
	public ForecastDTO createWeather(@Valid @RequestBody ForecastFormDTO form){
		Map<String, String> json;
		try {
			json = jsonHelper.parseJson(WeatherHelper.getWeather(form.getCity()));
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
		Forecast forecast = Forecast.builder()
				.email(form.getEmail())
				.temp(json.get("temp"))
				.description(json.get("description"))
				.city(json.get("name"))
				.build();

		Optional<ForecastDTO> forecastDTO = forecastService.createForecast(forecast);

		return forecastDTO.orElse(null);
	}

	@Operation(summary = "Get weathers")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Weathers was get",
					content = {
							@Content(mediaType = "application/json"
							)
					}
			)
	})
	@GetMapping("/weather")
	public List<ForecastDTO> getWeather(){
		return forecastService.getForecasts();
	}

	@Operation(summary = "Get weathers by city")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Weathers was get",
					content = {
							@Content(mediaType = "application/json"
							)
					}
			)
	})
	@GetMapping("/weather/{city}")
	public List<ForecastDTO> getForecastsByCity(@PathVariable String city){
		return forecastService.getForecastsInCity(city);
	}
}
