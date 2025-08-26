package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.dto.TransactionRequest;
import com.neueda.portfolio_management.entity.Transaction;

import java.util.List;

public interface TransactionService {
    public List<Transaction> getAllTransactions();
    public List<Transaction> getTransactionsByAsset(String asset);
    public Transaction createTransaction(TransactionRequest transactionRequest);
}
