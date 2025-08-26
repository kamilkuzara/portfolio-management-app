package com.neueda.portfolio_management.converter;

import com.neueda.portfolio_management.enums.TransactionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTypeConverterTest {

    private final TransactionTypeConverter converter = new TransactionTypeConverter();

    @Test
    public void convertToDatabaseColumn_null_returnsNull() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    public void convertToDatabaseColumn_enum_returnsUppercaseName() {
        // pick a concrete enum value; typical enums include BUY/SELL - use first available to be resilient
        TransactionType sample = TransactionType.values()[0];
        String db = converter.convertToDatabaseColumn(sample);
        assertNotNull(db);
        assertEquals(sample.name(), db);
    }

    @Test
    public void convertToEntityAttribute_null_returnsNull() {
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    public void convertToEntityAttribute_lowercase_returnsEnum() {
        TransactionType sample = TransactionType.values()[0];
        String lower = sample.name().toLowerCase();
        TransactionType result = converter.convertToEntityAttribute(lower);
        assertEquals(sample, result);
    }

    @Test
    public void convertToEntityAttribute_mixedcase_returnsEnum() {
        TransactionType sample = TransactionType.values()[0];
        String mixed = sample.name().substring(0,1).toLowerCase() + sample.name().substring(1);
        TransactionType result = converter.convertToEntityAttribute(mixed);
        assertEquals(sample, result);
    }

    @Test
    public void convertToEntityAttribute_invalid_throws() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("does_not_exist"));
    }
}
