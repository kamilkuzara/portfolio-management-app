package com.neueda.portfolio_management.service;

import java.util.Map;

public interface PortfolioService {
    public Double getPortfolioBalance();
    public Map<String, Double> getCurrentPrices();
}
