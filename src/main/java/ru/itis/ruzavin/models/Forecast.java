package ru.itis.ruzavin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "forecasts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Forecast {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String city;
	private String description;
	private String email;
	private String temp;
}
