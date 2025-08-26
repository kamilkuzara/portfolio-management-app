package com.neueda.portfolio_management.repository;

import com.neueda.portfolio_management.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    public List<Asset> findAllByNameContainingIgnoreCase(String name);
    public Optional<Asset> findByName(String name);
}
