package ru.itis.ruzavin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.ruzavin.dto.AppealDTO;
import ru.itis.ruzavin.models.Appeal;
import ru.itis.ruzavin.repositories.AppealRepository;
import ru.itis.ruzavin.services.interfaces.AppealService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppealServiceImpl implements AppealService {

	private final AppealRepository appealRepository;

	@Override
	public AppealDTO createAppeal(Appeal appeal) {
		return AppealDTO.fromModel(appealRepository.save(appeal));
	}

	@Override
	public List<AppealDTO> getAppealsByUserId(Integer id) {
		return AppealDTO.fromModel(appealRepository.getAppealsByUserId(id));
	}

	@Override
	public List<AppealDTO> getAppealsByCity(String city) {
		return AppealDTO.fromModel(appealRepository.getAppealsByForecastCityIgnoreCase(city));
	}
}
