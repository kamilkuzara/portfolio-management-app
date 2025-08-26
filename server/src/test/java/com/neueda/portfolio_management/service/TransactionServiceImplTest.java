package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.entity.Transaction;
import com.neueda.portfolio_management.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {

    private TransactionRepository repository;
    private TransactionServiceImpl service;

    @BeforeEach
    public void setup() {
        repository = mock(TransactionRepository.class);
        service = new TransactionServiceImpl(repository);
    }

    @Test
    public void getAllTransactions_delegatesToRepository() {
        Transaction t1 = new Transaction();
        Transaction t2 = new Transaction();
        when(repository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Transaction> res = service.getAllTransactions();
        assertEquals(2, res.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getTransactionsByAsset_delegatesToRepository_withExactParam() {
        String assetName = "Bitcoin";
        Transaction t = new Transaction();
        when(repository.findAllByAssetNameIgnoreCase(assetName)).thenReturn(Arrays.asList(t));

        List<Transaction> res = service.getTransactionsByAsset(assetName);
        assertEquals(1, res.size());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(repository).findAllByAssetNameIgnoreCase(captor.capture());
        assertEquals(assetName, captor.getValue());
    }
}
