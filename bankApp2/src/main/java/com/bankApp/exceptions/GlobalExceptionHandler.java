package com.bankApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BankAccountNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFoundException(BankAccountNotFoundException ex ){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	// 404 - not found 
	@ExceptionHandler(BankEmployeeNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFoundException(BankEmployeeNotFoundException ex ){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	// 405 = method not allowed
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
	// 400 = bad request
}
