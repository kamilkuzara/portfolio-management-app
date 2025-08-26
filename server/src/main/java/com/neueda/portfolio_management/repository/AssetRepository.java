package com.neueda.portfolio_management.repository;

import com.neueda.portfolio_management.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    public List<Asset> findAll();
    public List<Asset> findAllByQuantityGreaterThan(Double quantity);
    public List<Asset> findAllByNameContainingIgnoreCaseAndQuantityGreaterThan(String name, Double quantity);
    public Optional<Asset> findByName(String name);
}
