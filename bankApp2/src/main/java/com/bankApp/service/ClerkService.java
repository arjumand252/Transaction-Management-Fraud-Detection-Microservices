package com.bankApp.service;

import java.util.List;

import com.bankApp.dto.ClerkCreateRequest;
import com.bankApp.entities.Clerk;

public interface ClerkService {
	Clerk getById(Integer id);
	List<Clerk> getAll();
	void addClerk(ClerkCreateRequest clerkreq);
	void deleteClerk(Integer id);
	void updateClerk(Clerk clerk);
	public Clerk getByUserName(String name);
}
