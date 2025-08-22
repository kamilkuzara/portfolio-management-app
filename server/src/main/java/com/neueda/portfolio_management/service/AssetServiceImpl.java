package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.repository.AssetRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Profile("db")
public class AssetServiceImpl implements AssetService{
    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public List<Asset> getAllAssets(){
        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(Long id){
        try {
//            find the asset by id or return null if not found
            return assetRepository.findById(id).orElse(null);
        }catch(IllegalArgumentException illegalArgumentException){ // when null id provided
            return null;
        }
    }

    @Override
    public List<Asset> getAssetsByName(String name){
        return assetRepository.findAllByNameContainingIgnoreCase(name);
    }
}
