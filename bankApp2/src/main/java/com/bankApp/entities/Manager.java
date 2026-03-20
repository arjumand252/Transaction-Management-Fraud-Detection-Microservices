package com.bankApp.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends Employee {

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Clerk> clerks = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "manager_id_fk")
    @JsonManagedReference
//    private List<Account> accounts;

    private List<Account> accounts = new ArrayList<>();

    // Getters and Setters

	public Manager() {}

	public List<Clerk> getClerks() {
		return clerks;
	}

	public void setClerks(List<Clerk> clerks) {
		this.clerks = clerks;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
}
