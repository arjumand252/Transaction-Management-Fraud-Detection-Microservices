package com.bankApp.dto;

import java.math.BigDecimal;

public class TransferRequest {
    private int fromAccId;
    private int toAccId;
    private BigDecimal amount;
    private Integer clerkId;
    
	public int getFromAccId() {
		return fromAccId;
	}

	public int getToAccId() {
		return toAccId;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public Integer getClerkId() {
		return clerkId;
	}

}
