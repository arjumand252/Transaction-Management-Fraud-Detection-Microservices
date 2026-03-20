package com.bankApp.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.entities.Account;
import com.bankApp.service.AccountService;

/*
 * this controller is responsible for all end points related to account CRUD operations
 * TODO: rewrite all localhost links on postman to test these, add "account"
 */

@RestController
@RequestMapping(path="v1/accounts")

public class AccountController {
	
	private AccountService accountService;

	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping()
	public List<Account> getAll(){
		return accountService.getAll();
	}
	
	@GetMapping(path = "{id}")
	public Account getById(@PathVariable int id ){
		return accountService.getById(id);
	}
	
	@PostMapping(path = "addAccount")
	public void addAccount(@RequestBody Account account) {
		System.out.println("add account controller method called");
		accountService.addAccount(account);
	}
	
	@DeleteMapping(path = "{id}")
    public void deleteAccount(@PathVariable Integer id) {
    	accountService.deleteAccount(id);
    }
	
	@PostMapping(path = "updateAccount/{id}")
    public void updateAccount(@PathVariable Integer id, @RequestBody Account account) {
		account.setId(id);
    	accountService.updateAccount(account);
    }
    	
}