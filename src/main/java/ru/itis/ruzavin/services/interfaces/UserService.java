package ru.itis.ruzavin.services.interfaces;

import ru.itis.ruzavin.dto.CreateUserDTO;
import ru.itis.ruzavin.dto.SignInDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.models.User;

import java.util.Optional;

public interface UserService {
	Optional<UserDTO> saveUser(CreateUserDTO form, String url);
	Optional<UserDTO> signIn(SignInDTO form);
	boolean verify(String code);
	void sendVerificationMail(String mail, String name, String code, String url);
	UserDTO getUserById(Integer id);
}
