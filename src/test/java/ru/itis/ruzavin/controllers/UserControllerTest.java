package ru.itis.ruzavin.controllers;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.ruzavin.TestApp;
import ru.itis.ruzavin.models.User;
import ru.itis.ruzavin.repositories.UserRepository;

import java.util.Locale;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TestApp.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@BeforeAll
	public void init() {
		User user = new User();
		user.setEmail("1");
		user.setName("2");
		user.setPassword("3");
		user.setVerificationCode("4");
		userRepository.save(user);
	}

	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get("/user/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name".toLowerCase(Locale.ROOT)).value("ivan"));
	}

	@Test
	public void testGetAll() throws Exception {
		mockMvc.perform(get("/user")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name".toLowerCase(Locale.ROOT)).value("ivan"));
	}

	@Test
	public void testVerify() throws Exception {
		mockMvc.perform(get("/verification?code=4")).
				andExpect(status().isOk()).
				andExpect(content().string("verification_failed"));
	}

}
