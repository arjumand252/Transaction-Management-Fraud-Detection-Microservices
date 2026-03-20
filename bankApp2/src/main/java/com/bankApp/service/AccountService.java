package com.bankApp.service;

import java.util.List;

import com.bankApp.entities.Account;

public interface AccountService {
    public List<Account> getAll();
    public Account getById(int id);
	
    public void addAccount(Account account);
    public void deleteAccount(Integer id);
    public void updateAccount(Account account);
    
    public List<Account> getAccountsForManager(Integer managerId);
}