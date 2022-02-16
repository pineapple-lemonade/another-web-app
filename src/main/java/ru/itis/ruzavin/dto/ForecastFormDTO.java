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
public class ForecastFormDTO {
	@NotBlank(message = "City shouldn't be blank!")
	private String city;

	@NotBlank(message = "Email shouldn't be blank!")
	private String email;
}
