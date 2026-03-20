package com.bankApp.exceptions;

public class BankEmployeeNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public BankEmployeeNotFoundException(String message) {
		super(message);
	}

}
