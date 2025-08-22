package com.neueda.portfolio_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.service.AssetService;

import java.util.List;

@RestController
@RequestMapping("/assets")
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
//
//   @PostMapping
//   public void createCustomer(@RequestBody Customer customer){
//       customerService.createCustomer(customer);
//   }
//
//   @DeleteMapping("/{id}")
//   public void deleteCustomer(@PathVariable int id){
//    customerService.deleteCustomer(id);
//   }
}
