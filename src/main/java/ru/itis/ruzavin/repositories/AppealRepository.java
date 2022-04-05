package ru.itis.ruzavin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ruzavin.models.Appeal;

import java.util.List;

public interface AppealRepository extends JpaRepository<Appeal, Integer> {
	List<Appeal> getAppealsByUserId(Integer id);
	List<Appeal> getAppealsByForecastCity(String city);
}
