package com.bankApp.dao;

import java.util.List;
import java.util.Optional;

import com.bankApp.dto.ManagerClerkDTO;
import com.bankApp.entities.Manager;

public interface ManagerDao {
	Manager getById(Integer id);
	List<ManagerClerkDTO> getAll();
	void addManager(Manager manager);
	void deleteManager(Integer id);
	void updateManager(Manager manager);
	public Manager getByUserName(String name) ;
	Optional<Manager> findByManagerEmail(String username);
}
