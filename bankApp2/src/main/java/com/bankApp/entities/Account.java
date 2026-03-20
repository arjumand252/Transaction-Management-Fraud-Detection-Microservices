package com.bankApp.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal balance;
	private String nationality;
	private String phone;
	private LocalDate date;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("account-transactions")
	private List<Transaction> transactionList =new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "manager_id_fk")  // this column will store the manager_id
    @JsonBackReference
    private Manager manager;
    
	public Account(Integer id, String name, BigDecimal balance, String nation, String phone, LocalDate date) {
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.nationality = nation;
		this.phone = phone;
		this.date = date;
	}
	public Account(String name, BigDecimal balance, String nation, String phone) {
		this.name = name;
		this.balance = balance;
		this.nationality = nation;
		this.phone = phone;
	}
	public Account() {}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBalance() {
		return balance;	
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	} 
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@JsonIgnore
	@JsonManagedReference("account-transactions")
	public List<Transaction> getTransactions(){
		return transactionList;
	}
	public Transaction addTransaction(
	        TransactionType type,
	        BigDecimal amount,
	        Clerk clerk
	) {
	    Transaction tx = new Transaction();
	    tx.setTransactionType(type);
	    tx.setAmount(amount);
	    tx.setDate(LocalDateTime.now());
	    tx.setAccount(this);
	    tx.setClerk(clerk);

	    // approval rule
	    if (amount.compareTo(BigDecimal.valueOf(200_000)) > 0) {
	        tx.setStatus(TransactionStatus.PENDING);
	    } else {
	        tx.setStatus(TransactionStatus.APPROVED);
	    }

	    transactionList.add(tx);
	    
	    return tx;	
	}
	public void setManager(Manager mgr) {
		this.manager = mgr;
	}

}