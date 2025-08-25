package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.entity.Transaction;

import java.util.List;

public interface TransactionService {
    public List<Transaction> getAllTransactions();
}
