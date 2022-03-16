package ru.itis.ruzavin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ruzavin.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	Optional<User> getUserByEmailLike(String email);
	Optional<User> findUserByVerificationCodeLike(String code);
}
