package com.neueda.portfolio_management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neueda.portfolio_management.converter.TransactionTypeConverter;
import com.neueda.portfolio_management.enums.TransactionType;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Convert(converter = TransactionTypeConverter.class) // optional if autoApply = true
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "asset", nullable = false)
    private Asset asset;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Double quantity;
    private Double pricePerUnit;

    public Transaction() {
    }

    public Transaction(Long id, TransactionType type, Asset asset, LocalDate date, Double quantity, Double pricePerUnit) {
        this.id = id;
        this.type = type;
        this.asset = asset;
        this.date = date;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
