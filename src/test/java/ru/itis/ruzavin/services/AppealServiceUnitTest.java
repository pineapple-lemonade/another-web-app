package ru.itis.ruzavin.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.ruzavin.dto.AppealDTO;
import ru.itis.ruzavin.models.Appeal;
import ru.itis.ruzavin.models.Forecast;
import ru.itis.ruzavin.models.User;
import ru.itis.ruzavin.repositories.AppealRepository;
import ru.itis.ruzavin.services.interfaces.AppealService;

import java.util.List;

@RunWith(SpringRunner.class)
public class AppealServiceUnitTest {
	@TestConfiguration
	static class UserServiceTestContextConfiguration {

		@MockBean
		private AppealRepository appealRepository;

		@Bean
		public AppealService AppealService(AppealRepository appealRepository) {
			return new AppealServiceImpl(appealRepository);
		}
	}

	@Autowired
	private AppealService appealService;

	@Test
	public void testCreateAppeal() {
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

		AppealDTO appeal1 = appealService.createAppeal(appeal);

		Assert.assertNull(appeal1);
	}

	@Test
	public void testGetAppealsByUserId() {
		List<AppealDTO> appealsByUserId = appealService.getAppealsByUserId(1);
		Assert.assertTrue(appealsByUserId.isEmpty());
	}

	@Test
	public void testGetAppealsByCity() {
		List<AppealDTO> appealsByCity = appealService.getAppealsByCity("test");
		Assert.assertTrue(appealsByCity.isEmpty());
	}
}
