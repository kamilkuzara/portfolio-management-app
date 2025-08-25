package com.neueda.portfolio_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PortfolioItemRequest {

    @NotNull(message = "Asset ID cannot be null")
    private Long assetId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
