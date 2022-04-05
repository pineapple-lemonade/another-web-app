package ru.itis.ruzavin.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.ruzavin.dto.ForecastDTO;
import ru.itis.ruzavin.models.Forecast;
import ru.itis.ruzavin.repositories.ForecastRepository;
import ru.itis.ruzavin.repositories.UserRepository;
import ru.itis.ruzavin.services.interfaces.AppealService;
import ru.itis.ruzavin.services.interfaces.ForecastService;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ForecastServiceUnitTest {

	@TestConfiguration
	static class UserServiceTestContextConfiguration {

		@MockBean
		private ForecastRepository weatherRepository;

		@MockBean
		private UserRepository userRepository;

		@MockBean
		private AppealService appealService;

		@Bean
		public ForecastService WeatherService(ForecastRepository weatherRepository) {
			return new ForecastServiceImpl(weatherRepository, userRepository, appealService);
		}
	}

	@Autowired
	private ForecastService forecastService;

	@Test
	public void testCreateForecast() {
		Forecast forecast = Forecast.builder()
				.city("test")
				.temp("test")
				.description("test")
				.email("test")
				.build();

		Optional<ForecastDTO> forecast1 = forecastService.createForecast(forecast);

		Assert.assertFalse(forecast1.isPresent());
	}

	@Test
	public void testGetForecasts() {
		List<ForecastDTO> forecasts = forecastService.getForecasts();
		Assert.assertTrue(forecasts.isEmpty());
	}

	@Test
	public void testGetForecastsInCity() {
		List<ForecastDTO> forecasts = forecastService.getForecastsInCity("test");
		Assert.assertTrue(forecasts.isEmpty());
	}
}
