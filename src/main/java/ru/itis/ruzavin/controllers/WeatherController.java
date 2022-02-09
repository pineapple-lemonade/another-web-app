package ru.itis.ruzavin.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.ruzavin.helpers.WeatherHelper;

import java.util.Optional;

@RestController
public class WeatherController {

	@GetMapping("/weather")
	public String getWeather(@RequestParam Optional<String> city){
		return WeatherHelper.getWeather(city.orElse("Kazan"));
	}
}
