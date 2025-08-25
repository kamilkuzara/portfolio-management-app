package com.neueda.portfolio_management.repository;

import com.neueda.portfolio_management.entity.Asset;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
  public List<Asset> findAllByNameContainingIgnoreCase(String name);
}
