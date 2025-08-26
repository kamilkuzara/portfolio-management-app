package com.neueda.portfolio_management.controller;

import com.neueda.portfolio_management.service.PortfolioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    private PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/balance")
    public Double getBalance() {
        return portfolioService.getPortfolioBalance();
    }

    @GetMapping("/currentAssetPrices")
    public Map<String, Double> getCurrentPrices() {
        return portfolioService.getCurrentPrices();
    }
}
