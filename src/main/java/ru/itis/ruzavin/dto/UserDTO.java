package ru.itis.ruzavin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ruzavin.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
	private Integer id;
	private String name;
	private String email;
	private String password;

	public static UserDTO fromModel(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.name(user.getName())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();
	}
}
