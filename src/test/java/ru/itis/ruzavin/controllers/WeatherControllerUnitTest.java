package ru.itis.ruzavin.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.ruzavin.dto.ForecastDTO;
import ru.itis.ruzavin.helpers.JsonHelper;
import ru.itis.ruzavin.models.Forecast;
import ru.itis.ruzavin.repositories.ForecastRepository;
import ru.itis.ruzavin.services.interfaces.ForecastService;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ForecastService forecastService;

	@MockBean
	private ForecastRepository forecastRepository;

	@MockBean
	private JsonHelper jsonHelper;

	@Before
	public void init() {
		Forecast forecast = Forecast.builder()
				.city("uchaly")
				.build();

		given(forecastService.createForecast(forecast)).willReturn(Optional.of(ForecastDTO.fromModel(forecast)));
	}

	@Test
	public void testCreateForecast() throws Exception {
		mockMvc.perform(post("/weather")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].city").value("uchaly"));
	}
}
