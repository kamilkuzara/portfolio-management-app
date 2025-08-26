package com.neueda.portfolio_management.entity;

import com.neueda.portfolio_management.enums.TransactionType;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void constructorAndGetters_shouldReturnValues() {
        Asset asset = new Asset(); // ...existing code... (use default constructor from project)
        Transaction tx = new Transaction(1L, TransactionType.values()[0], asset, "2023-01-01", 100L, 12.34);

        assertEquals(1L, tx.getId());
        assertEquals(TransactionType.values()[0], tx.getType());
        assertSame(asset, tx.getAsset());
        assertEquals("2023-01-01", tx.getDate());
        assertEquals(100L, tx.getQuantity());
        // use reflection because the public getter/setter for price may not exist in the entity
        assertEquals(Double.valueOf(12.34), getPrice(tx));
    }

    @Test
    public void setters_shouldUpdateValues() {
        Transaction tx = new Transaction();
        assertNull(tx.getId());

        tx.setId(5L);
        assertEquals(5L, tx.getId());

        tx.setType(TransactionType.values()[0]);
        assertEquals(TransactionType.values()[0], tx.getType());

        Asset asset = new Asset();
        tx.setAsset(asset);
        assertSame(asset, tx.getAsset());

        tx.setDate("2020-12-31");
        assertEquals("2020-12-31", tx.getDate());

        tx.setQuantity(250L);
        assertEquals(250L, tx.getQuantity());

        // use reflection to set and read pricePerUnitInUSD because setters/getters may be absent
        setPrice(tx, Double.valueOf(99.99));
        assertEquals(Double.valueOf(99.99), getPrice(tx));
    }

    // Reflection helpers to read/write the private pricePerUnitInUSD field reliably
    private static Double getPrice(Transaction tx) {
        try {
            Field f = Transaction.class.getDeclaredField("pricePerUnitInUSD");
            f.setAccessible(true);
            return (Double) f.get(tx);
        } catch (NoSuchFieldException nsf) {
            throw new AssertionError("Field pricePerUnitInUSD not found on Transaction", nsf);
        } catch (IllegalAccessException iae) {
            throw new AssertionError("Unable to access pricePerUnitInUSD", iae);
        }
    }

    private static void setPrice(Transaction tx, Double value) {
        try {
            Field f = Transaction.class.getDeclaredField("pricePerUnitInUSD");
            f.setAccessible(true);
            f.set(tx, value);
        } catch (NoSuchFieldException nsf) {
            throw new AssertionError("Field pricePerUnitInUSD not found on Transaction", nsf);
        } catch (IllegalAccessException iae) {
            throw new AssertionError("Unable to access pricePerUnitInUSD", iae);
        }
    }
}
