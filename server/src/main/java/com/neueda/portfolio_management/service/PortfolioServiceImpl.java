package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService{
    @Value("${twelvedata.api.key}")
    private String apiKey;

    private AssetRepository assetRepository;
    private final RestTemplate restTemplate;

    public PortfolioServiceImpl(AssetRepository assetRepository, RestTemplate restTemplate) {
        this.assetRepository = assetRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Double getPortfolioBalance() {
        Map<String, Double> stockValues = new HashMap<>();
        List<String> portfolioAssetNames = assetRepository.findAllByQuantityGreaterThan(0.0).stream().map(asset -> asset.getName()).collect(Collectors.toList());

        String symbolParam = String.join(",", portfolioAssetNames);

        String url = "https://api.twelvedata.com/price?symbol=" + symbolParam + "&apikey=" + apiKey;

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> body = response.getBody();

                // If multiple symbols, Twelve Data returns a map with each symbol as key
                if (portfolioAssetNames.size() > 1) {
                    for (String symbol : portfolioAssetNames) {
                        Map<String, Object> symbolData = (Map<String, Object>) body.get(symbol);
                        if (symbolData != null && symbolData.get("price") != null) {
                            stockValues.put(symbol, Double.parseDouble(symbolData.get("price").toString()));
                        }
                    }
                } else {
                    // Single symbol returns a "price" field
                    if (body.get("price") != null) {
                        stockValues.put(portfolioAssetNames.get(0), Double.parseDouble(body.get("price").toString()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // You could throw a custom exception here if needed
        }

        double totalValue = 0;
        for(Map.Entry<String, Double> entry : stockValues.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            totalValue += assetRepository.findByName(entry.getKey()).map(asset -> asset.getQuantity().doubleValue() * entry.getValue().doubleValue()).orElse(0.0);
        }

        return totalValue;
    }

    @Override
    public Map<String, Double> getCurrentPrices(){
        Map<String, Double> stockValues = new HashMap<>();
        List<String> portfolioAssetNames = assetRepository.findAllByQuantityGreaterThan(0.0).stream().map(asset -> asset.getName()).collect(Collectors.toList());

        System.out.println("portfolioAssetNames: " + portfolioAssetNames.toString());

        String symbolParam = String.join(",", portfolioAssetNames);

        String url = "https://api.twelvedata.com/price?symbol=" + symbolParam + "&apikey=" + apiKey;

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> body = response.getBody();
                System.out.println(body.toString());

                // If multiple symbols, Twelve Data returns a map with each symbol as key
                if (portfolioAssetNames.size() > 1) {
                    for (String symbol : portfolioAssetNames) {
                        Map<String, Object> symbolData = (Map<String, Object>) body.get(symbol);
                        if (symbolData != null && symbolData.get("price") != null) {
                            stockValues.put(symbol, Double.parseDouble(symbolData.get("price").toString()));
                        }
                    }
                } else {
                    // Single symbol returns a "price" field
                    if (body.get("price") != null) {
                        stockValues.put(portfolioAssetNames.get(0), Double.parseDouble(body.get("price").toString()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // You could throw a custom exception here if needed
        }

        return stockValues;
    }
}
