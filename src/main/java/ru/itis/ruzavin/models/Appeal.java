package ru.itis.ruzavin.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "appeals")
public class Appeal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String date;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "forecast_id")
	private Forecast forecast;
}
