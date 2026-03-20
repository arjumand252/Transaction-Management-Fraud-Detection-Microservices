package com.bankApp.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("CLERK")
public class Clerk extends Employee {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id_fk", nullable = false)
    @JsonBackReference
    private Manager manager;

    @OneToMany(mappedBy = "clerk")
    @JsonManagedReference("clerk-transactions")
    private List<Transaction> transactions;

    // Getters and Setters
    
	public Clerk() {}

	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
