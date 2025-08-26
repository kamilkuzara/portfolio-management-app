package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.entity.Transaction;
import com.neueda.portfolio_management.enums.TransactionType;
import com.neueda.portfolio_management.repository.AssetRepository;
import com.neueda.portfolio_management.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {

    private TransactionRepository transactionRepository;
    private AssetRepository assetRepository;
    private TransactionServiceImpl service;

    @BeforeEach
    public void setup() {
        transactionRepository = mock(TransactionRepository.class);
        assetRepository = mock(AssetRepository.class);
        service = new TransactionServiceImpl(transactionRepository, assetRepository);
    }

    @Test
    public void getAllTransactions_delegatesToRepository() {
        Transaction t1 = new Transaction(1L, TransactionType.values()[0], new Asset(), LocalDate.parse("2023-01-01"), 100.0, 12.34);
        Transaction t2 = new Transaction(1L, TransactionType.values()[0], new Asset(), LocalDate.parse("2023-01-01"), 100.0, 12.34);
        when(transactionRepository.findAllByOrderByDateAsc()).thenReturn(Arrays.asList(t1, t2));

        List<Transaction> res = service.getAllTransactions();
        assertEquals(2, res.size());
        verify(transactionRepository, times(1)).findAllByOrderByDateAsc();
    }

    @Test
    public void getTransactionsByAsset_delegatesToRepository_withExactParam() {
        String assetName = "Bitcoin";
        Transaction t = new Transaction();
        when(transactionRepository.findAllByAssetNameIgnoreCaseOrderByDateAsc(assetName))
                .thenReturn(Arrays.asList(t));

        List<Transaction> res = service.getTransactionsByAsset(assetName);
        assertEquals(1, res.size());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(transactionRepository).findAllByAssetNameIgnoreCaseOrderByDateAsc(captor.capture());
        assertEquals(assetName, captor.getValue());
    }
}
