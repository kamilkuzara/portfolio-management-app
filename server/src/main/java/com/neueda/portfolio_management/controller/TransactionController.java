package com.neueda.portfolio_management.controller;

import com.neueda.portfolio_management.dto.TransactionRequest;
import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.entity.Transaction;
import com.neueda.portfolio_management.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping(params = "asset")
    public List<Transaction> getAllTransactionsByAsset(@RequestParam String asset) {
        return transactionService.getTransactionsByAsset(asset);
    }

    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }
}
