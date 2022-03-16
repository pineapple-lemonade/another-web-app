package ru.itis.ruzavin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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

	@PostMapping("/weather")
	public ForecastDTO createWeather(@Valid @RequestBody ForecastFormDTO form, Authentication auth){
		Map<String, String> json;
		try {
			json = jsonHelper.parseJson(WeatherHelper.getWeather(form.getCity()));
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
		Forecast forecast = Forecast.builder()
				.email(auth.getName())
				.temp(json.get("temp"))
				.description(json.get("description"))
				.city(json.get("name"))
				.build();

		Optional<ForecastDTO> forecastDTO = forecastService.createForecast(forecast);

		return forecastDTO.orElse(null);
	}

	@GetMapping("/weather")
	public List<ForecastDTO> getWeather(){
		return forecastService.getForecasts();
	}

	@GetMapping("/weather/{city}")
	public List<ForecastDTO> getForecastsByCity(@PathVariable String city){
		return forecastService.getForecastsInCity(city);
	}
}
