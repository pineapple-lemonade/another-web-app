package ru.itis.ruzavin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ruzavin.models.Forecast;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForecastDTO {
	private Integer id;
	private String city;
	private String description;
	private String email;
	private String temp;
	private AppealDTO appealDTO;

	public static ForecastDTO fromModel(Forecast forecast) {
		return ForecastDTO.builder()
				.city(forecast.getCity())
				.description(forecast.getDescription())
				.id(forecast.getId())
				.temp(forecast.getTemp())
				.email(forecast.getEmail())
				.build();
	}

	public static List<ForecastDTO> fromModel(List<Forecast> forecasts) {
		return forecasts.stream()
				.map(ForecastDTO::fromModel)
				.collect(Collectors.toList());
	}
}
