package ru.itis.ruzavin.services.interfaces;

import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.models.User;

import java.util.Optional;

public interface UserService {
	Optional<UserDTO> saveUser(User user);
}
