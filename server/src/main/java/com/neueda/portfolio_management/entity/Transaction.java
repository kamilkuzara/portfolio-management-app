package com.neueda.portfolio_management.entity;

import com.neueda.portfolio_management.converter.TransactionTypeConverter;
import com.neueda.portfolio_management.enums.TransactionType;
import jakarta.persistence.*;

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

    private String date;
    private Long quantity;
    private Double pricePerUnitInUSD;

    public Transaction() {
    }

    public Transaction(Long id, TransactionType type, Asset asset, String date, Long quantity, Double pricePerUnitInUSD) {
        this.id = id;
        this.type = type;
        this.asset = asset;
        this.date = date;
        this.quantity = quantity;
        this.pricePerUnitInUSD = pricePerUnitInUSD;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return pricePerUnitInUSD;
    }

    public void setPrice(Double pricePerUnitInUSD) {
        this.pricePerUnitInUSD = pricePerUnitInUSD;
    }
}
