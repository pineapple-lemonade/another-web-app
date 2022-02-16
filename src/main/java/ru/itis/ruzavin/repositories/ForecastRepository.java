package ru.itis.ruzavin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ruzavin.models.Forecast;

public interface ForecastRepository extends JpaRepository<Forecast, Integer> {

}
