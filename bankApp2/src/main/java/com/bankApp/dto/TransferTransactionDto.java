package com.bankApp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankApp.entities.TransactionStatus;
import com.bankApp.entities.TransactionType;

public record TransferTransactionDto(
    Integer transactionId,
    BigDecimal amount,
    LocalDateTime date,
    TransactionStatus status,
    TransactionType transactionType,
    Integer fromAccountId,
    Integer toAccountId,
    Integer clerkId
) {}
