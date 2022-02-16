package ru.itis.ruzavin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDTO {
	@NotBlank(message = "Name shouldn't be blank!")
	private String name;

	@NotBlank(message = "Email shouldn't be blank!")
	private String email;

	@NotBlank(message = "Password shouldn't be blank!")
	private String password;
}
