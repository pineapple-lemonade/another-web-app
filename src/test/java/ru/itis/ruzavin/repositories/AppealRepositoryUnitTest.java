package ru.itis.ruzavin.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.ruzavin.models.Appeal;
import ru.itis.ruzavin.models.Forecast;
import ru.itis.ruzavin.models.User;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppealRepositoryUnitTest {
	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private AppealRepository appealRepository;



	@Test
	public void testGetAppealsByUserId() {
		Appeal appeal = Appeal.builder()
				.date("1")
				.user(User.builder()
						.name("test")
						.email("test")
						.password("testtest")
						.build())
				.forecast(Forecast.builder()
						.city("test")
						.description("test")
						.temp("test")
						.email("test")
						.build())
				.build();
		Integer id = testEntityManager.persistAndGetId(appeal.getUser(), Integer.class);
		testEntityManager.persist(appeal.getForecast());
		testEntityManager.persist(appeal);
		testEntityManager.flush();

		List<Appeal> appealsByUserId = appealRepository.getAppealsByUserId(id);

		Assert.assertEquals(appealsByUserId.get(0).getForecast().getCity(), "test");
	}

	@Test
	public void testGetAppealsByCity() {
		Appeal appeal = Appeal.builder()
				.date("1")
				.user(User.builder()
						.name("test")
						.email("test")
						.password("testtest")
						.build())
				.forecast(Forecast.builder()
						.city("test")
						.description("test")
						.temp("test")
						.email("test")
						.build())
				.build();

		appeal.getForecast().setAppeal(appeal);

		testEntityManager.persist(appeal.getUser());
		testEntityManager.persist(appeal.getForecast());
		testEntityManager.persist(appeal);
		testEntityManager.flush();

		List<Appeal> appealsByForecastCityIgnoreCase = appealRepository.getAppealsByForecastCity("test");

		Assert.assertEquals(appealsByForecastCityIgnoreCase.get(0).getForecast().getCity(), "test");
	}
}
