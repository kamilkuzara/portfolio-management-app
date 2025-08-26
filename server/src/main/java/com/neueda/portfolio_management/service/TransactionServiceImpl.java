package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.entity.Transaction;
import com.neueda.portfolio_management.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Profile("db")
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByAsset(String asset){
        return transactionRepository.findAllByAssetNameIgnoreCaseOrderByDateAsc(asset);
    }
}
