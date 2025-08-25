package com.neueda.portfolio_management.controller;

import com.neueda.portfolio_management.dto.AssetRequest;
import com.neueda.portfolio_management.dto.AssetUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.service.AssetService;

import java.util.List;

@RestController
@RequestMapping("/assets")
@CrossOrigin(origins = "*")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<Asset> getAllAssets(){
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public Asset getAssetById(@PathVariable long id){
        return assetService.getAssetById(id);
    }

    @GetMapping(params = "name")
    public List<Asset> getAssetsByName(@RequestParam String name){
        return assetService.getAssetsByName(name);
    }

   @PostMapping
   public Asset createAsset(@Valid @RequestBody AssetRequest assetRequest){
       return assetService.createAsset(assetRequest);
   }

   @DeleteMapping("/{id}")
   public Asset deleteAsset(@PathVariable long id){
        return assetService.deleteAsset(id);
   }

   @PutMapping("/{id}")
    public Asset updateAsset(@PathVariable long id, @Valid @RequestBody AssetUpdateRequest assetUpdateRequest){
        return assetService.updateAsset(id, assetUpdateRequest);
   }
}
