package ru.itis.ruzavin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ruzavin.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
