package com.bankApp.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankApp.dao.ManagerDao;
import com.bankApp.dto.ManagerClerkDTO;
import com.bankApp.entities.Manager;
import com.bankApp.exceptions.BankEmployeeNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{
	private ManagerDao managerDao;
	private final PasswordEncoder passwordEncoder;

    public ManagerServiceImpl(ManagerDao managerDao,
                              PasswordEncoder passwordEncoder) {
        this.managerDao = managerDao;
        this.passwordEncoder = passwordEncoder;
    }

	public void approveTransaction(int transactionId) {
//	    to be done later
//		needs logic to access transactions by id
	}


	@Override
	public Manager getById(Integer id) {
		try {
			return managerDao.getById(id);
		} catch(EntityNotFoundException ex) {
			throw new BankEmployeeNotFoundException("Manager with id= "+id+" not found.");
		}
	}
	@Override
	public Manager getByUserName(String name) {
		try {
			return managerDao.getByUserName(name);
		} catch(EntityNotFoundException ex) {
			System.out.println("emp not found");
			throw new BankEmployeeNotFoundException("Manager with name ="+name+" not found.");
		}
	}

	@Override
	public List<ManagerClerkDTO> getAll() {
		return managerDao.getAll();
	}

	@Override
	public void addManager(Manager manager) {
	    if (manager.getPassword() == null || manager.getPassword().isBlank()) {
	        throw new IllegalArgumentException("Password cannot be null or empty");
	    }

	    // Encode password before saving
	    String encodedPassword = passwordEncoder.encode(manager.getPassword());
	    manager.setPassword(encodedPassword);

	    managerDao.addManager(manager);
	}

	@Override
	public void deleteManager(Integer id) {
		managerDao.deleteManager(id);
	}

	@Override
	public void updateManager(Manager manager) {
		managerDao.updateManager(manager);
	}

}
