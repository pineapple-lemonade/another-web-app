package ru.itis.ruzavin.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.ruzavin.dto.CreateUserDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.models.User;
import ru.itis.ruzavin.repositories.UserRepository;
import ru.itis.ruzavin.services.interfaces.UserService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerUnitTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private UserController userController;

	@Test
	public void testSignUp() throws Exception {
		User user = User.builder()
				.password("33333333333")
				.name("test")
				.email("test")
				.verificationCode("5445")
				.build();

		CreateUserDTO form = CreateUserDTO.builder()
				.email(user.getEmail())
				.name(user.getEmail())
				.password(user.getPassword())
				.build();

		given(userService.saveUser(form ,"")).willReturn(Optional.of(UserDTO.fromModel(user)));

		mockMvc.perform(post("/signUp")
						.contentType(MediaType.APPLICATION_JSON)
				.flashAttr("user", form))
				.andExpect(status().isOk())
				.andExpect(view().name("signUp"));

	}

//	@Test
//	public void testVerify() throws Exception {
//		User user = User.builder()
//				.password("33333333333")
//				.name("test")
//				.email("test")
//				.verificationCode("5445")
//				.build();
//
//		given(userService.verify(user.getVerificationCode())).willReturn(true);
//
//		mockMvc.perform(get("/verification?code=5445"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("verification_success"));
//	}
}
