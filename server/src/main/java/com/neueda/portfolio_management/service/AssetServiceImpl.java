package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.dto.AssetRequest;
import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.repository.AssetRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.OptimisticLockingFailureException;
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
//            TODO: should probably raise an exception and return a 404 response
            return assetRepository.findById(id).orElse(null);
        }catch(IllegalArgumentException illegalArgumentException){ // when null id provided
            return null;
        }
    }

    @Override
    public List<Asset> getAssetsByName(String name){
        return assetRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public Asset createAsset(AssetRequest assetRequest){
        Asset asset = new Asset();

//        set all fields from the data provided in the request
        asset.setName(assetRequest.getName());
        asset.setType(assetRequest.getType());

        return assetRepository.saveAndFlush(asset);
    }

    @Override
    public Asset deleteAsset(Long id){
        try {
            Asset asset = assetRepository.findById(id).orElse(null);
            assetRepository.deleteById(id);
            return asset;
        }catch(IllegalArgumentException e) { // when null is provided as id
            return null;
        }catch(OptimisticLockingFailureException e){ // supposedly thrown when there is no record in the database
            return null;
        }
    }
}
