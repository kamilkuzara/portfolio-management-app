package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.dto.AssetRequest;
import com.neueda.portfolio_management.dto.AssetUpdateRequest;
import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.repository.AssetRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Asset getAssetById(Long id) {
        if (id == null) {
            return null; // Return null immediately if ID is null
        }
        try {
            return assetRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException illegalArgumentException) {
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
        asset.setQuantity(assetRequest.getQuantity());

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

    @Override
    public Asset updateAsset(Long id, AssetUpdateRequest assetUpdateRequest){
        Optional<Asset> assetOptional = assetRepository.findById(id);

//        if no record in the database to update
        if(!assetOptional.isPresent())
            return null;

        Asset asset = assetOptional.get();

//        update the fields only if provided in the query
        if(assetUpdateRequest.getName() != null)
            asset.setName(assetUpdateRequest.getName());
        if(assetUpdateRequest.getType() != null)
            asset.setType(assetUpdateRequest.getType());
        if(assetUpdateRequest.getQuantity() != null)
            asset.setQuantity(assetUpdateRequest.getQuantity());

        return assetRepository.saveAndFlush(asset);
    }
}
