package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.entity.PortfolioItem;
import com.neueda.portfolio_management.repository.PortfolioItemRepository;
import org.springframework.stereotype.Service;

@Service
public class PortfolioItemServiceImpl implements PortfolioItemService {
    private final PortfolioItemRepository portfolioItemRepository;

    public PortfolioItemServiceImpl(PortfolioItemRepository portfolioItemRepository) {
        this.portfolioItemRepository = portfolioItemRepository;
    }

    @Override
    public PortfolioItem getPortfolioItemByAssetId(Long assetId) {
        return portfolioItemRepository.findByAssetId(assetId).orElse(null);
    }
}
