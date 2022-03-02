package ru.itis.ruzavin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.ruzavin.models.Appeal;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppealDTO {
	private Integer id;

	private String date;

	private UserDTO user;

	private ForecastDTO forecast;

	public static AppealDTO fromModel(Appeal appeal){
		return AppealDTO.builder()
				.date(appeal.getDate())
				.user(UserDTO.fromModel(appeal.getUser()))
				.forecast(ForecastDTO.fromModel(appeal.getForecast()))
				.id(appeal.getId())
				.build();
	}

	public static List<AppealDTO> fromModel(List<Appeal> appeals) {
		return appeals.stream()
				.map(AppealDTO::fromModel)
				.collect(Collectors.toList());
	}
}
