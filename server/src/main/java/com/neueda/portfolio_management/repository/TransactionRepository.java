package com.neueda.portfolio_management.repository;

import com.neueda.portfolio_management.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
