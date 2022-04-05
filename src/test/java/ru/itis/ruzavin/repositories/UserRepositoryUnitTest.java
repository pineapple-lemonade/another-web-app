package ru.itis.ruzavin.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.ruzavin.models.User;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryUnitTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindByEmail() {
		User user = User.builder()
				.email("test")
				.name("test")
				.password("testtest")
				.isEnable(true)
				.build();

		testEntityManager.persistAndFlush(user);

		User byEmail = userRepository.findByEmail(user.getEmail());

		Assert.assertEquals(byEmail.getEmail(), user.getEmail());
	}

	@Test
	public void testGetByEmail() {
		User user = User.builder()
				.email("test")
				.name("test")
				.password("testtest")
				.isEnable(true)
				.build();

		testEntityManager.persistAndFlush(user);

		Optional<User> userByEmailLike = userRepository.getUserByEmailLike(user.getEmail());

		Assert.assertEquals(userByEmailLike.get().getEmail(), user.getEmail());
	}

	@Test
	public void testGetByVerificationCode() {
		User user = User.builder()
				.email("test")
				.name("test")
				.password("testtest")
				.isEnable(true)
				.verificationCode("test")
				.build();

		testEntityManager.persistAndFlush(user);

		Optional<User> userByVerificationCodeLike = userRepository.findUserByVerificationCodeLike(user.getVerificationCode());

		Assert.assertEquals(userByVerificationCodeLike.get().getVerificationCode(), user.getVerificationCode());
	}

}
