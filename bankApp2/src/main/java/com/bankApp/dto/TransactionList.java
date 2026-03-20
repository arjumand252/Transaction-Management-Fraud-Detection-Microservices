package com.bankApp.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankApp.entities.TransactionType;

public class TransactionList {

    private BigDecimal amount;
    private String transactionType;
    private LocalDateTime date;

    public TransactionList(BigDecimal amount, TransactionType transactionType, LocalDateTime date) {
    	this.amount = amount;
        this.transactionType = transactionType.toString();
        this.date = date;
    }

    // Getters and Setters
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
