package ru.itis.ruzavin.services.interfaces;

import ru.itis.ruzavin.dto.ForecastDTO;
import ru.itis.ruzavin.models.Forecast;

import java.util.List;
import java.util.Optional;

public interface ForecastService {
	Optional<ForecastDTO> saveForecast(Forecast forecast);
	List<ForecastDTO> getForecasts();
}
