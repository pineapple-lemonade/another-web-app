package ru.itis.ruzavin.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.ruzavin.dto.ForecastDTO;
import ru.itis.ruzavin.dto.ForecastFormDTO;
import ru.itis.ruzavin.helpers.JsonHelper;
import ru.itis.ruzavin.models.Appeal;
import ru.itis.ruzavin.models.Forecast;
import ru.itis.ruzavin.repositories.ForecastRepository;
import ru.itis.ruzavin.repositories.UserRepository;
import ru.itis.ruzavin.services.interfaces.ForecastService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
@AutoConfigureMockMvc
public class WeatherControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ForecastService forecastService;

	@MockBean
	private ForecastRepository forecastRepository;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private JsonHelper jsonHelper;

	@MockBean
	private WeatherController weatherController;

	@Test
	public void testCreateForecast() throws Exception {
		Forecast forecast = Forecast.builder()
				.city("uchaly")
				.email("gogogogo")
				.id(1)
				.description("ddddd")
				.temp("34")
				.appeal(Appeal.builder()
						.id(3)
						.date("date")
						.build())
				.build();

		given(forecastService.createForecast(forecast)).willReturn(Optional.of(ForecastDTO.fromModel(forecast)));

		ForecastFormDTO form = ForecastFormDTO.builder()
				.city("uchaly")
				.email("gogogogo")
				.build();

		given(weatherController.createWeather(form)).willReturn(ForecastDTO.fromModel(forecast));

		mockMvc.perform(post("/weather")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								    "city":"uchaly",
								    "email":"gogogogo"
								}"""))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.city").value("uchaly"));
	}

	@Test
	public void testGetForecasts() throws Exception {
		Forecast forecast = Forecast.builder()
				.city("uchaly")
				.email("chel")
				.build();

		given(weatherController.getWeather()).willReturn(List.of(ForecastDTO.fromModel(forecast)));

		mockMvc.perform(get("/weather")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].city").value("uchaly"));
	}

	@Test
	public void getForecastsByCity() throws Exception {
		Forecast forecast = Forecast.builder()
				.city("uchaly")
				.email("chel")
				.build();

		given(weatherController.getForecastsByCity("uchaly")).willReturn(List.of(ForecastDTO.fromModel(forecast)));

		mockMvc.perform(get("/weather/uchaly")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].city").value("uchaly"));
	}
}