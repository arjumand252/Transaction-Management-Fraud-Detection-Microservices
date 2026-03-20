package com.bankApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.dto.DepositRequest;
import com.bankApp.dto.DepositTransactionDto;
import com.bankApp.dto.TransactionList;
import com.bankApp.dto.TransferRequest;
import com.bankApp.dto.TransferTransactionDto;
import com.bankApp.dto.WithdrawRequest;
import com.bankApp.dto.WithdrawTransactionDto;
import com.bankApp.service.TransactionService;

/*
 * This controller has additional methods introduced in the service layer pertaining to our business logic
 * TODO: 
 */

@RestController
@RequestMapping(path = "v1")
public class TransactionController {
	
	private TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping("transfer")
	public TransferTransactionDto transfer(@RequestBody TransferRequest request) {
		return transactionService.transfer(
	        request.getFromAccId(),
	        request.getToAccId(),
	        request.getAmount(),
	        request.getClerkId()
	    );
	}
	
	@PostMapping(path = "accounts/deposit")
    public DepositTransactionDto deposit(@RequestBody DepositRequest depositReq) {
		return transactionService.deposit(depositReq.getId(), depositReq.getAmount(), depositReq.getClerkId());
    }
	
	@PostMapping(path = "accounts/withdraw")
    public WithdrawTransactionDto withdraw(@RequestBody WithdrawRequest withdrawReq) {
		return transactionService.withdraw(withdrawReq.getId(), withdrawReq.getAmount(), withdrawReq.getClerkId());
    }
	
	@GetMapping(path = "accounts/{accId}/getTransaction")
	public List<TransactionList> getTransactions(@PathVariable(name="accId") Integer id){
		return transactionService.getTransactions(id);
	}
}
