package com.bankApp.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankApp.dao.ClerkDao;
import com.bankApp.dto.ClerkCreateRequest;
import com.bankApp.entities.Clerk;
import com.bankApp.entities.Manager;
import com.bankApp.exceptions.BankEmployeeNotFoundException;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClerkServiceImpl implements ClerkService{

    // private final CommandLineRunner loadData;
	
	private ClerkDao clerkDao;
	private ManagerService managerService;
    private final PasswordEncoder passwordEncoder;
	
	public ClerkServiceImpl(ClerkDao clerkDao, ManagerService managerService, PasswordEncoder passwordEncoder
		// CommandLineRunner loadData
		) {
		this.clerkDao = clerkDao;
		this.managerService = managerService;
		this.passwordEncoder=  passwordEncoder;
		// this.loadData = loadData;
	}

	@Override
	public Clerk getById(Integer id) {
//		try {
//			return clerkDao.getById(id);
//		} catch(BankEmployeeNotFoundException ex) {
//			throw new BankEmployeeNotFoundException("Clerk with id= "+id+" not found.");
//		}
		return clerkDao.getById(id);
	}
	public Clerk getByUserName(String name) {
		return clerkDao.getByUserName(name);
	}
	@Override
	public List<Clerk> getAll() {
		return clerkDao.getAll();
	}

	@Override
	public void addClerk(ClerkCreateRequest clerkreq) {
	    try {
	    	Manager manager = managerService.getById(clerkreq.managerId);
	    	String encodedPass = passwordEncoder.encode(clerkreq.password);
		    Clerk clerk = new Clerk();
		    clerk.setName(clerkreq.clerkName);
		    clerk.setManager(manager);
		    clerk.setTransactions(new ArrayList<>());
		    clerk.setPassword(encodedPass);
		    clerkDao.addClerk(clerk);
	    } catch (PersistenceException ex) {
	        // Wrap lower-level exceptions in a service-specific exception if desired
	        throw new RuntimeException("Failed to add account", ex);
	    }
	}

	@Override
	public void deleteClerk(Integer id) {
		clerkDao.deleteClerk(id);
	}

	@Override
	public void updateClerk(Clerk clerk) {
		clerkDao.updateClerk(clerk);
	}

}
