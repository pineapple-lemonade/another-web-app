package ru.itis.ruzavin.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.ruzavin.dto.AppealDTO;
import ru.itis.ruzavin.dto.ForecastDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.repositories.UserRepository;
import ru.itis.ruzavin.services.interfaces.AppealService;
import ru.itis.ruzavin.services.interfaces.UserService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AppealController.class)
@AutoConfigureMockMvc
public class AppealControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private AppealService appealService;

	@Test
	public void testGetAppealByUserId() throws Exception {
		AppealDTO appealDTO = AppealDTO.builder()
				.date("1")
				.id(1)
				.user(UserDTO.builder()
						.name("test")
						.id(1)
						.email("test")
						.password("test")
						.build())
				.forecast(ForecastDTO.builder()
						.city("test")
						.description("test")
						.temp("test")
						.email("test")
						.build())
				.build();

		given(appealService.getAppealsByUserId(1)).willReturn(List.of(appealDTO));

		mockMvc.perform(get("/appeal/user/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("[{\"id\":1,\"date\":\"1\",\"user\":{\"id\":1,\"name\":\"test\",\"email\":\"test\",\"password\":\"test\"},\"forecast\":{\"id\":null,\"city\":\"test\",\"description\":\"test\",\"email\":\"test\",\"temp\":\"test\",\"appealDTO\":null}}]"));
	}

	@Test
	public void testGetAppealByCity() throws Exception {
		AppealDTO appealDTO = AppealDTO.builder()
				.date("1")
				.id(1)
				.user(UserDTO.builder()
						.name("test")
						.id(1)
						.email("test")
						.password("test")
						.build())
				.forecast(ForecastDTO.builder()
						.city("test")
						.description("test")
						.temp("test")
						.email("test")
						.build())
				.build();

		given(appealService.getAppealsByCity("test")).willReturn(List.of(appealDTO));

		mockMvc.perform(get("/appeal/city/test"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("[{\"id\":1,\"date\":\"1\",\"user\":{\"id\":1,\"name\":\"test\",\"email\":\"test\",\"password\":\"test\"},\"forecast\":{\"id\":null,\"city\":\"test\",\"description\":\"test\",\"email\":\"test\",\"temp\":\"test\",\"appealDTO\":null}}]"));
	}

}
