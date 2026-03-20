package com.bankApp.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transactionId;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
    private TransactionType transactionType;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;
	
    private BigDecimal amount;
    private LocalDateTime date;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id_fk", nullable = false)
    @JsonBackReference("account-transactions")
    private Account account;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clerk_id_fk", nullable = false)
    @JsonBackReference("clerk-transactions")
    private Clerk clerk;

	

	public Transaction(TransactionType transactionType, TransactionStatus status,
			BigDecimal amount, LocalDateTime date, Account account, Clerk clerk) {
		this.transactionType = transactionType;
		this.status = status;
		this.amount = amount;
		this.date = date;
		this.account = account;
		this.clerk = clerk;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus staus) {
		this.status = staus;
	}

	public Transaction() {}
	
	public Clerk getClerk() {
		return clerk;
	}

	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
    
}
