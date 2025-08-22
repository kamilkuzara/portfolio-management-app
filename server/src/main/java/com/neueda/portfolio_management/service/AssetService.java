package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.dto.AssetRequest;
import com.neueda.portfolio_management.dto.AssetUpdateRequest;
import com.neueda.portfolio_management.entity.Asset;

import java.util.List;

public interface AssetService {
    public List<Asset> getAllAssets();
    public Asset getAssetById(Long id);
    public List<Asset> getAssetsByName(String name);
    public Asset createAsset(AssetRequest assetRequest);
    public Asset deleteAsset(Long id);
    public Asset updateAsset(Long id, AssetUpdateRequest assetUpdateRequest);
}
