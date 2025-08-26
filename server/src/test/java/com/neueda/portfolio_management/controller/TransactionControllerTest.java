package com.neueda.portfolio_management.controller;

import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.entity.Transaction;
import com.neueda.portfolio_management.enums.TransactionType;
import com.neueda.portfolio_management.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TransactionControllerTest {

    private MockMvc mockMvc;
    private TransactionService transactionService;

    @BeforeEach
    public void setup() {
        transactionService = mock(TransactionService.class);
        TransactionController controller = new TransactionController(transactionService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllTransactions_returnsList() throws Exception {
        Transaction t1 = new Transaction(1L, TransactionType.values()[0], new Asset(), "2023-01-01", 10L, 5.0);
        Transaction t2 = new Transaction(2L, TransactionType.values()[0], new Asset(), "2023-01-02", 20L, 6.0);

        when(transactionService.getAllTransactions()).thenReturn(Arrays.asList(t1, t2));

        mockMvc.perform(get("/transactions").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));

        verify(transactionService, times(1)).getAllTransactions();
    }

    @Test
    public void getAllTransactionsByAsset_callsServiceWithParam() throws Exception {
        String assetName = "ETH";
        Transaction t = new Transaction(3L, TransactionType.values()[0], new Asset(), "2023-01-03", 5L, 1.2);
        when(transactionService.getTransactionsByAsset(assetName)).thenReturn(Arrays.asList(t));

        mockMvc.perform(get("/transactions").param("asset", assetName).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(transactionService, times(1)).getTransactionsByAsset(assetName);
    }

    // New: verify JSON payload contains expected fields including nested asset and enum type
    @Test
    public void getAllTransactions_returnsDetailedJson() throws Exception {
        Asset asset = new Asset(11L, "BTC", "crypto", 150.5);
        Transaction tx = new Transaction(10L, TransactionType.values()[0], asset, "2023-02-02", 50L, 1234.5);
        when(transactionService.getAllTransactions()).thenReturn(Arrays.asList(tx));

        mockMvc.perform(get("/transactions").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(10))
                .andExpect(jsonPath("$[0].date").value("2023-02-02"))
                .andExpect(jsonPath("$[0].quantity").value(50))
                // enum serialized as string
                .andExpect(jsonPath("$[0].type").value(TransactionType.values()[0].name()))
                // nested asset fields
                .andExpect(jsonPath("$[0].asset.id").value(11))
                .andExpect(jsonPath("$[0].asset.name").value("BTC"))
                .andExpect(jsonPath("$[0].asset.type").value("crypto"))
                .andExpect(jsonPath("$[0].asset.quantity").value(150.5));

        verify(transactionService, times(1)).getAllTransactions();
    }

    // New: empty list handling
    @Test
    public void getAllTransactions_returnsEmptyList() throws Exception {
        when(transactionService.getAllTransactions()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/transactions").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(transactionService, times(1)).getAllTransactions();
    }

    // New: service throws -> controller invocation throws an exception (no global handler present)
    @Test
    public void getAllTransactions_serviceThrows_throwsException() throws Exception {
        when(transactionService.getAllTransactions()).thenThrow(new RuntimeException("boom"));

        assertThrows(Exception.class, () -> {
            mockMvc.perform(get("/transactions").accept(MediaType.APPLICATION_JSON));
        });

        verify(transactionService, times(1)).getAllTransactions();
    }

    // New: service throws for asset-filtered endpoint -> invocation throws an exception
    @Test
    public void getAllTransactionsByAsset_serviceThrows_throwsException() throws Exception {
        String assetName = "ETH";
        when(transactionService.getTransactionsByAsset(assetName)).thenThrow(new RuntimeException("boom"));

        assertThrows(Exception.class, () -> {
            mockMvc.perform(get("/transactions").param("asset", assetName).accept(MediaType.APPLICATION_JSON));
        });

        verify(transactionService, times(1)).getTransactionsByAsset(assetName);
    }
}
