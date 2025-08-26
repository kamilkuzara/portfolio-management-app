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

class AssetServiceImplTest {

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
        Asset asset1 = new Asset(1L, "Stock A", "Equity", 150.5);
        Asset asset2 = new Asset(2L, "Bond B", "Fixed Income", 25.0);

        when(assetRepository.findAll()).thenReturn(Arrays.asList(asset1, asset2));

        List<Asset> result = assetService.getAllAssets();

        assertEquals(2, result.size());
        verify(assetRepository, times(1)).findAll();
    }

    @Test
    void testGetAssetById_Found() {
        Asset asset = new Asset(1L, "Stock A", "Equity", 150.5);

        when(assetRepository.findById(1L)).thenReturn(Optional.of(asset));

        Asset result = assetService.getAssetById(1L);

        assertNotNull(result);
        assertEquals("Stock A", result.getName());
        verify(assetRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAssetById_NotFound() {
        when(assetRepository.findById(99L)).thenReturn(Optional.empty());

        Asset result = assetService.getAssetById(99L);

        assertNull(result);
        verify(assetRepository, times(1)).findById(99L);
    }

    @Test
    void testGetAssetById_NullId() {
        Asset result = assetService.getAssetById(null);

        assertNull(result);
        verify(assetRepository, never()).findById(any());
    }

    @Test
    void testCreateAsset() {
        AssetRequest request = new AssetRequest();
        request.setName("Stock A");
        request.setType("Equity");

        Asset savedAsset = new Asset(1L, "Stock A", "Equity", 150.5);

        when(assetRepository.saveAndFlush(any(Asset.class))).thenReturn(savedAsset);

        Asset result = assetService.createAsset(request);

        assertNotNull(result);
        assertEquals("Stock A", result.getName());
        assertEquals("Equity", result.getType());
        assertEquals(150.5, result.getQuantity(), 0);
        verify(assetRepository, times(1)).saveAndFlush(any(Asset.class));
    }
}