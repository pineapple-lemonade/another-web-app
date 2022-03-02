package ru.itis.ruzavin.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.ruzavin.dto.AppealDTO;
import ru.itis.ruzavin.dto.ForecastDTO;
import ru.itis.ruzavin.dto.UserDTO;
import ru.itis.ruzavin.models.Appeal;
import ru.itis.ruzavin.models.Forecast;
import ru.itis.ruzavin.models.User;
import ru.itis.ruzavin.repositories.ForecastRepository;
import ru.itis.ruzavin.repositories.UserRepository;
import ru.itis.ruzavin.services.interfaces.AppealService;
import ru.itis.ruzavin.services.interfaces.ForecastService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {

	private final ForecastRepository forecastRepository;
	private final UserRepository userRepository;
	private final AppealService appealService;

	@Transactional
	@Override
	public Optional<ForecastDTO> createForecast(Forecast forecast) {
		User user = userRepository.findByEmail(forecast.getEmail());
		System.out.println(user);
		if (user != null){
			LocalDateTime dateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

			forecastRepository.save(forecast);
			log.warn(forecast + "is saved");

			Appeal appeal = Appeal.builder()
					.forecast(forecast)
					.user(user)
					.date(formatter.format(dateTime))
					.build();
			appealService.createAppeal(appeal);
			log.warn(appeal + "is saved");

			return Optional.of(ForecastDTO.fromModel(forecast));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<ForecastDTO> getForecasts() {
		return forecastRepository.findAll().stream()
				.map(ForecastDTO::fromModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<ForecastDTO> getForecastsInCity(String city) {
		return ForecastDTO.fromModel(forecastRepository.getForecastsByCityIgnoreCase(city));
	}
}
