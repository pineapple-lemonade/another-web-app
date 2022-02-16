package ru.itis.ruzavin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.ruzavin.dto.ForecastDTO;
import ru.itis.ruzavin.models.Forecast;
import ru.itis.ruzavin.repositories.ForecastRepository;
import ru.itis.ruzavin.repositories.UserRepository;
import ru.itis.ruzavin.services.interfaces.ForecastService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {

	private final ForecastRepository forecastRepository;
	private final UserRepository userRepository;

	@Transactional
	@Override
	public Optional<ForecastDTO> saveForecast(Forecast forecast) {
		if (userRepository.findByEmail(forecast.getEmail()) != null){
			forecastRepository.save(forecast);
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
}
