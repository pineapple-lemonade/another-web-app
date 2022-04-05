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
import ru.itis.ruzavin.repositories.UserRepository;
import ru.itis.ruzavin.services.interfaces.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
@AutoConfigureMockMvc
public class HelloControllerUnitTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void testGetIndexPage() throws Exception {
		mockMvc.perform(get("/")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


	@Test
	public void testHome() throws Exception {
		mockMvc.perform(get("/home")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is3xxRedirection());
	}

	@Test
	public void testSignUp() throws Exception {
		mockMvc.perform(get("/signUp"))
				.andExpect(status().isOk())
				.andExpect(view().name("signUp"));
	}
}
