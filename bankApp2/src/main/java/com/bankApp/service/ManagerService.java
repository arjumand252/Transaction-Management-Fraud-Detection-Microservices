package com.bankApp.service;

import java.util.List;

import com.bankApp.dto.ManagerClerkDTO;
import com.bankApp.entities.Manager;

public interface ManagerService {
	Manager getById(Integer id);
	List<ManagerClerkDTO> getAll();
	void addManager(Manager manager);
	void deleteManager(Integer id);
	void updateManager(Manager manager);
	public Manager getByUserName(String name) ;
}
