package com.neueda.portfolio_management.repository;

import com.neueda.portfolio_management.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
