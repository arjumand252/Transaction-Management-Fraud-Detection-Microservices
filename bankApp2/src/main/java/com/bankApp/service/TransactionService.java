package com.bankApp.service;

import java.math.BigDecimal;
import java.util.List;

import com.bankApp.dto.DepositTransactionDto;
import com.bankApp.dto.PendingTransactionDto;
import com.bankApp.dto.TransactionList;
import com.bankApp.dto.TransferTransactionDto;
import com.bankApp.dto.WithdrawTransactionDto;
import com.bankApp.entities.Transaction;

public interface TransactionService {
    // additional methods in the service layer pertaining to our business logic
	TransferTransactionDto transfer(int fromAccId, int toAccId, BigDecimal amount, Integer clerkId);
    DepositTransactionDto deposit(int accId, BigDecimal amount, Integer clerkId);
    WithdrawTransactionDto withdraw(int accId, BigDecimal amount, Integer clerkId);
    List<TransactionList> getTransactions(int id);
	Transaction getById(int transactionId);
    List<PendingTransactionDto> findPendingTransactionsForManager(Integer managerId);
}
