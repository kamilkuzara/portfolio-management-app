package com.neueda.portfolio_management.controller;

import com.neueda.portfolio_management.entity.PortfolioItem;
import com.neueda.portfolio_management.service.PortfolioItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio-items")
public class PortfolioItemController {
    private final PortfolioItemService portfolioItemService;

    public PortfolioItemController(PortfolioItemService portfolioItemService) {
        this.portfolioItemService = portfolioItemService;
    }

    @GetMapping("/quantity/{assetId}")
    public int getQuantityByAssetId(@PathVariable Long assetId) {
        PortfolioItem portfolioItem = portfolioItemService.getPortfolioItemByAssetId(assetId);
        return portfolioItem != null ? portfolioItem.getQuantity() : 0;
    }
}
