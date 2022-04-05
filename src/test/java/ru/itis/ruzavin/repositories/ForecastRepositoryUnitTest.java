package ru.itis.ruzavin.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.ruzavin.models.Forecast;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ForecastRepositoryUnitTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private ForecastRepository forecastRepository;

	@Test
	public void testGetForecastsByCity() {
		Forecast forecast = Forecast.builder()
				.city("test")
				.temp("test")
				.description("test")
				.email("test")
				.build();

		testEntityManager.persistAndFlush(forecast);

		List<Forecast> forecastsByCityIgnoreCase = forecastRepository.getForecastsByCityIgnoreCase(forecast.getCity());

		Assert.assertEquals(forecastsByCityIgnoreCase.get(0).getCity(), forecast.getCity());
	}
}
