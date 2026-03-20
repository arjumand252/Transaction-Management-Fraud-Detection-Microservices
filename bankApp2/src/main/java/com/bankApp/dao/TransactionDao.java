package com.bankApp.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bankApp.dto.PendingTransactionDto;
import com.bankApp.entities.Account;
import com.bankApp.entities.Clerk;
import com.bankApp.entities.Transaction;
import com.bankApp.entities.TransactionStatus;
import com.bankApp.entities.TransactionType;

//@Repository
//public interface TransactionDao extends JpaRepository<Transaction, Integer>{
//
//    @Query("""
//    	    SELECT new com.bankApp.dto.PendingTransactionDto(
//    		    t.transactionId,
//		        t.amount,
//		        t.date,
//		        t.status,
//		        t.transactionType,
//		        a.id
//    	    )
//    	    FROM Transaction t
//    	    JOIN t.account a
//    	    JOIN a.manager m
//    	    WHERE t.status = :status
//    	      AND m.id = :managerId
//    	""")
//    	List<PendingTransactionDto> findPendingTransactionsForManager(
//    	        @Param("status") TransactionStatus status,
//    	        @Param("managerId") Integer managerId
//    	);
//}
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

    @Query("""
        SELECT new com.bankApp.dto.PendingTransactionDto(
            t.transactionId,
            t.amount,
            t.date,
            t.status,
            t.transactionType,
            a.id
        )
        FROM Transaction t
        JOIN t.account a
        WHERE t.status = :status
    """)
    List<PendingTransactionDto> findAllPendingTransactionsForManager(
            @Param("status") TransactionStatus status, Integer mgrId
    );

}
