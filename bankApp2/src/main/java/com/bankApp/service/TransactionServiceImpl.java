package com.bankApp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bankApp.dao.AccountDao;
import com.bankApp.dao.TransactionDao;
import com.bankApp.dto.DepositTransactionDto;
import com.bankApp.dto.PendingTransactionDto;
import com.bankApp.dto.TransactionList;
import com.bankApp.dto.TransferTransactionDto;
import com.bankApp.dto.WithdrawTransactionDto;
import com.bankApp.entities.Account;
import com.bankApp.entities.Clerk;
import com.bankApp.entities.Transaction;
import com.bankApp.entities.TransactionStatus;
import com.bankApp.entities.TransactionType;
import com.bankApp.exceptions.BankAccountNotFoundException;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
	private AccountDao accountDao;
	private AccountService accountService;
	private TransactionDao transactionDao;
	private ClerkService clerkService;

	@Autowired
	private KafkaTemplate<String, Transaction> kafkaTemplate;
	
	public TransactionServiceImpl(AccountDao accountDao, AccountService accountService, 
									TransactionDao transactionDao, ClerkService clerkService) {
		this.accountDao = accountDao;
		this.accountService = accountService;
		this.transactionDao = transactionDao;
		this.clerkService = clerkService;
	}

	@Override
	public TransferTransactionDto transfer(int fromAccId, int toAccId, BigDecimal amount, Integer clerkId) {
	    Account fromAcc = accountService.getById(fromAccId);
	    Account toAcc = accountService.getById(toAccId);

	    Clerk clerk = clerkService.getById(clerkId);
	    
	    fromAcc.addTransaction(TransactionType.TRANSFER_OUT, amount, clerk);
	    if (fromAcc.getBalance().compareTo(amount) < 0) {
	        throw new IllegalArgumentException(
	            "Insufficient balance in account " + fromAcc.getId()
	        );
	    }
	    Transaction tx =
	        fromAcc.getTransactions().get(fromAcc.getTransactions().size() - 1);
	    transactionDao.save(tx);
		kafkaTemplate.send("transactions-topic", tx);

	    if (tx.getStatus() == TransactionStatus.APPROVED) {
	        fromAcc.setBalance(fromAcc.getBalance().subtract(amount));
	        toAcc.setBalance(toAcc.getBalance().add(amount));

	        toAcc.addTransaction(TransactionType.TRANSFER_IN, amount, clerk);
	    }
	    accountDao.updateAccount(fromAcc);
	    accountDao.updateAccount(toAcc);
	    
	    return new TransferTransactionDto(
	    		tx.getTransactionId(),
	    	    tx.getAmount(),
	    	    tx.getDate(),
	    	    tx.getStatus(),
	    	    tx.getTransactionType(),
	    	    fromAccId,
	    	    toAccId,
	    	    clerk.getId()
	    );
	}

	@Override
	public DepositTransactionDto deposit(int accId, BigDecimal amount, Integer clerkId) {
	    Account acc = accountService.getById(accId);
	    acc.setBalance(acc.getBalance().add(amount));
	    
	    Clerk clerk = clerkService.getById(clerkId);
	    Transaction tx = acc.addTransaction(TransactionType.DEPOSIT, amount, clerk);
	    transactionDao.save(tx);
	    
	    accountDao.updateAccount(acc);
	    return new DepositTransactionDto(
	    		tx.getTransactionId(),
	    	    tx.getAmount(),
	    	    tx.getDate(),
	    	    tx.getStatus(),
	    	    tx.getTransactionType(),
	    	    accId,
	    	    clerk.getId()
	    );
	}
	
	// added new logic to ensure mgr approval is asked for withdrawal > 2 lakhs
	@Override
	public WithdrawTransactionDto withdraw(int accId, BigDecimal amount, Integer clerkId) {
	    Account acc = accountService.getById(accId);

	    // create transaction first
	    Clerk clerk = clerkService.getById(clerkId);
	    Transaction tx = acc.addTransaction(TransactionType.WITHDRAWAL, amount, clerk);
	    transactionDao.save(tx);
//	    Transaction lastTx =
//	        acc.getTransactions().get(acc.getTransactions().size() - 1);

	    // only deduct if approved
	    if (tx.getStatus() == TransactionStatus.APPROVED) {
	        acc.setBalance(acc.getBalance().subtract(amount));
	    }

	    accountDao.updateAccount(acc);
	    
	    return new WithdrawTransactionDto(
	    	    tx.getTransactionId(),
	    	    tx.getAmount(),
	    	    tx.getDate(),
	    	    tx.getStatus(),
	    	    tx.getTransactionType(),
	    	    accId,
	    	    clerk.getId()
	    	);
	}
	
	@Override
	public List<TransactionList> getTransactions(int id){
		Account account = accountDao.getById(id);
		if(account == null) {
			throw new BankAccountNotFoundException("Account not found");
		}
		
		List<Transaction> transactions = account.getTransactions();
		System.out.println(
			    "TX: " + transactions.get(0).getAmount()
			);

		// Map to DTO for REST response
		List<TransactionList> dtoList = transactions.stream()
//		        .map(tx -> new TransactionList(tx.getAmount(), tx.getTransactionType(), tx.getDate()))
//		        .collect(Collectors.toList());
				.map(tx -> new TransactionList(
				        tx.getAmount(),
				        tx.getTransactionType(),
				        tx.getDate()
				    ))
				    .collect(Collectors.toList());
		
		return dtoList;
		
	}

	@Override
	public Transaction getById(int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PendingTransactionDto> findPendingTransactionsForManager(Integer managerId) {
        return transactionDao.findAllPendingTransactionsForManager(
                TransactionStatus.PENDING,
                managerId
        );
    }
}
