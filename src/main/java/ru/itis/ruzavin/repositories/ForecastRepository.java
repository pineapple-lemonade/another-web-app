package ru.itis.ruzavin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ruzavin.models.Forecast;

import java.util.List;

public interface ForecastRepository extends JpaRepository<Forecast, Integer> {
	List<Forecast> getForecastsByCityIgnoreCase(String city);
}
