package com.bankApp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bankApp.entities.Account;
import com.bankApp.exceptions.BankEmployeeNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

/*
 * TODO;  Ensure dao only throws persistence exceptions/ EntityNotFoundException
 */

@Repository
public class AccountDaoImpl implements AccountDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Account> getAll() {
		return em.createQuery("SELECT a FROM Account a LEFT JOIN FETCH a.transactionList", Account.class).getResultList();
	}
	
	@Override
	public Account getById(int id) {
		Account account = em.find(Account.class, id);
	    if (account == null) {
	        throw new BankEmployeeNotFoundException("Account not found");
	    }
	    return account;
	}
	
	@Override
	public void updateAccount(Account account) {
		Account existing = new Account();
	    try {
	    	existing = em.find(Account.class, account.getId());
	    } catch (PersistenceException ex) {
	    	throw new IllegalArgumentException("Account not found");
	    }

	    if (existing == null) {
	        throw new BankEmployeeNotFoundException("Manager not found");
	    }

	    if (existing.getName() != null)
	        existing.setName(account.getName());

	    // AccountDataAccessException to be made later
	//  catch (PersistenceException e) {
//	      throw new AccountDataAccessException("Error updating account", e);
	//  }
	    
	}


	@Override
	public void addAccount(Account account) {
		try {
//			System.out.println("add account dao method called");
			em.persist(account);
		}
		catch(PersistenceException ex) {
			// to be done later
			System.out.println("Need to handle gracefully.");
		}
	}

	@Override
	public void deleteAccount(Integer id) {
			Account account = em.find(Account.class, id);
			try{
				em.remove(account);
			} catch(PersistenceException ex) {
				System.out.println("Invalid account id.");
			}
	}
	@Override
	public List<Account> findByManagerManagerId(Integer managerId) {
	    String jpql = "SELECT a FROM Account a WHERE a.manager.managerId = :managerId";
	    return em.createQuery(jpql, Account.class)
	             .setParameter("managerId", managerId)
	             .getResultList();
	}

}
