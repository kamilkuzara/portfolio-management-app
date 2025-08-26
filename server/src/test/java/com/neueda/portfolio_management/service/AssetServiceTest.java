package com.neueda.portfolio_management.service;

import com.neueda.portfolio_management.dto.AssetRequest;
import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.repository.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssetServiceTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetServiceImpl assetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAssets() {
        Asset asset1 = new Asset(1L, "Gold", "commodity", 150.5);
        Asset asset2 = new Asset(2L, "Silver", "commodity",  25.0);

        when(assetRepository.findAllByQuantityGreaterThan(0.0)).thenReturn(Arrays.asList(asset1, asset2));

        List<Asset> result = assetService.getAllAssets();

        assertEquals(2, result.size());
        verify(assetRepository, times(1)).findAllByQuantityGreaterThan(0.0);
    }

    @Test
    void testGetAssetById_Found() {
        Asset asset = new Asset(1L, "Gold", "commodity", 150.5);

        when(assetRepository.findById(1L)).thenReturn(Optional.of(asset));

        Asset result = assetService.getAssetById(1L);

        assertNotNull(result);
        assertEquals("Gold", result.getName());
        verify(assetRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateAsset() {
        AssetRequest request = new AssetRequest();
        request.setName("Gold");
        request.setType("commodity");
        request.setQuantity(150.5);

        Asset savedAsset = new Asset(1L, "Gold", "commodity", 150.5);

        when(assetRepository.saveAndFlush(any(Asset.class))).thenReturn(savedAsset);

        Asset result = assetService.createAsset(request);

        assertNotNull(result);
        assertEquals("Gold", result.getName());
        assertEquals("commodity", result.getType());
        assertEquals(150.5, result.getQuantity());
        verify(assetRepository, times(1)).saveAndFlush(any(Asset.class));
    }
}
