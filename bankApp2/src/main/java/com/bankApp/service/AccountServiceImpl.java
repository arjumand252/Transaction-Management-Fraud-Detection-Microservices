package com.bankApp.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bankApp.dao.AccountDao;
import com.bankApp.entities.Account;
import com.bankApp.exceptions.BankAccountNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

//	@Autowired
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public List<Account> getAll() {
		return accountDao.getAll();
	}

	@Override
	public Account getById(int id) {
		try {
			return accountDao.getById(id);
		} 
		catch(EntityNotFoundException ex) {
			throw new BankAccountNotFoundException("Account with id= "+id+" not found.");
		}
	}

	@Override
	public void addAccount(Account account) {
		if (account == null) {
	        throw new IllegalArgumentException("Account cannot be null");
	    }

	    // Optional: validate required fields (name, initial balance, etc.)
	    if (account.getBalance() == null) {
	        account.setBalance(BigDecimal.ZERO);
	    }

	    try {
	        accountDao.addAccount(account);
	    } catch (PersistenceException ex) {
	        // Wrap lower-level exceptions in a service-specific exception if desired
//	    	System.out.println("Persistence exc occurred.");
	        throw new RuntimeException("Failed to add account", ex);
	    }
	}
	// when the service method addAccount() ends, the persistenceContext of Dao layer is closed and 
	// then the INSERT query is executed on DB, Id value is assigned to the entity.

	@Override
	public void deleteAccount(Integer id) {
		accountDao.deleteAccount(id);
	}

	@Override
	public void updateAccount(Account account) {
		accountDao.updateAccount(account);	
	}
	
	public List<Account> getAccountsForManager(Integer managerId) {
	    return accountDao.findByManagerManagerId(managerId);
	}
}
