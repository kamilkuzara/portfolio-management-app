package com.neueda.portfolio_management.dto;

import jakarta.validation.constraints.NotBlank;

public class AssetRequest {
    @NotBlank(message = "Name of the asset missing")
    private String name;

    @NotBlank(message = "Type of the asset missing")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
