package com.neueda.portfolio_management.dto;

import com.neueda.portfolio_management.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class TransactionRequest {
    @NotNull(message = "Date of transaction is missing")
    private LocalDate date;

    @NotNull(message = "Transaction type is missing")
    private TransactionType type;

    @Positive
    @NotNull(message = "Transaction quantity is missing")
    private Double quantity;

    @Positive
    @NotNull(message = "Price per unit is missing")
    private Double pricePerUnit;

    // both the id and an asset object can be provided in the request
    // in such a case, the id should take precedence and the asset object should be discarded
    private Long assetId;
    private String assetName;
    private String assetType;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
}
