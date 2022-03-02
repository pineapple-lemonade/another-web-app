package ru.itis.ruzavin.services.interfaces;

import ru.itis.ruzavin.dto.AppealDTO;
import ru.itis.ruzavin.models.Appeal;

import java.util.List;

public interface AppealService {
	AppealDTO createAppeal(Appeal appeal);
	List<AppealDTO> getAppealsByUserId(Integer id);
	List<AppealDTO> getAppealsByCity(String city);
}
