package com.neueda.portfolio_management.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {

    @Test
    public void interface_and_implementation_relationship() {
        // The interface itself has no behavior to test; implementations contain logic.
        assertTrue(TransactionService.class.isInterface(), "TransactionService should be an interface");

        // Ensure there is at least one implementation wired in the codebase
        assertTrue(TransactionService.class.isAssignableFrom(TransactionServiceImpl.class),
                "TransactionServiceImpl should implement TransactionService");
    }
}
