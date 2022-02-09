package ru.itis.ruzavin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ruzavin.model.User;

import javax.persistence.Id;

public interface UserRepository extends JpaRepository<User, Id> {
}
