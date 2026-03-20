package com.bankApp.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.dto.ManagerClerkDTO;
import com.bankApp.dto.PendingTransactionDto;
import com.bankApp.entities.Account;
import com.bankApp.entities.Manager;
import com.bankApp.service.AccountService;
import com.bankApp.service.ManagerService;
import com.bankApp.service.TransactionService;

@RestController
@RequestMapping(path = "v1/managers")
public class ManagerController {
	private ManagerService managerService;
	private AccountService accountService;
	private TransactionService transactionService;

	public ManagerController(ManagerService managerService, AccountService accountService, TransactionService transactionService) {
		this.managerService = managerService;
		this.accountService = accountService;
		this.transactionService = transactionService;
	}
	@GetMapping()
	public List<ManagerClerkDTO> getAll(){
		return managerService.getAll();
	}
	
	@GetMapping(path = "getById/{id}")
	public Manager getById(@PathVariable int id ){
		return managerService.getById(id);
	}
	
	@PostMapping(path = "addManager")
	public void addManager(@RequestBody Manager manager) {
//		System.out.println("add account controller method called");
		managerService.addManager(manager);
	}
	
	@DeleteMapping(path = "{id}")
    public void deleteManager(@PathVariable Integer id) {
		managerService.deleteManager(id);
    }
	
	@PutMapping(path = "updateManager/{id}")
	public void updateManager(
	        @PathVariable Integer id,
	        @RequestBody Manager manager) {

	    manager.setId(id);
	    managerService.updateManager(manager);
	}
	
	@GetMapping("/accounts")
	public List<Account> getAccounts(Authentication authentication) {
//        Integer id = Integer.parseInt(authentication.getName()); 
        // ID is now username
//        Manager manager = managerService.getById(id); // your existing method
//        return accountService.getAccountsForManager(manager.getManagerId());
        
        Integer managerId = Integer.parseInt(authentication.getName());
        return accountService.getAccountsForManager(managerId);
    }
	
	@GetMapping("/{mgr_id}/pendingTransactions")
	public List<PendingTransactionDto> getPendingTransactions(@PathVariable Integer mgr_id){
		return transactionService.findPendingTransactionsForManager(mgr_id);
	}
}
