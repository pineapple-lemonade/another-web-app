package ru.itis.ruzavin.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.ruzavin.config.MailConfig;
import ru.itis.ruzavin.dto.CreateUserDTO;
import ru.itis.ruzavin.dto.SignInDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.repositories.UserRepository;
import ru.itis.ruzavin.services.interfaces.UserService;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class UserServiceUnitTest {
	@TestConfiguration
	static class UserServiceTestContextConfiguration {

		@MockBean
		private UserRepository userRepository;

		@MockBean
		private BCryptPasswordEncoder encoder;

		@MockBean
		private JavaMailSender javaMailSender;

		@MockBean
		private MailConfig mailConfig;


		@Bean
		public UserService userService() {
			return new UserServiceImpl(userRepository, encoder, javaMailSender, mailConfig);
		}
	}

	@Autowired
	private UserService userService;

	@Test
	public void testSaveUser() {
		CreateUserDTO form = CreateUserDTO.builder()
				.password("testtest")
				.name("test")
				.email("test")
				.build();

		Optional<UserDTO> userDTO = userService.saveUser(form, "");

		Assert.assertEquals(userDTO.get().getName(), form.getName());
	}

	@Test
	public void testSignIn() {
		SignInDTO form = SignInDTO.builder()
				.password("testtest")
				.email("test")
				.build();

		CreateUserDTO formSave = CreateUserDTO.builder()
				.password("testtest")
				.name("test")
				.email("test")
				.build();

		Optional<UserDTO> saveUser = userService.saveUser(formSave, "");

		Optional<UserDTO> userDTO = userService.signIn(form);

		Assert.assertEquals(saveUser.get().getEmail(), form.getEmail());
	}

	@Test
	public void testGetUserById() {
		UserDTO userById = userService.getUserById(1);
		Assert.assertNull(userById);
	}

	@Test
	public void testGetAllUsers() {
		List<UserDTO> allUsers = userService.getAllUsers();
		Assert.assertTrue(allUsers.isEmpty());
	}
}
