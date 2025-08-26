package com.neueda.portfolio_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.portfolio_management.dto.AssetRequest;
import com.neueda.portfolio_management.entity.Asset;
import com.neueda.portfolio_management.service.AssetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AssetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AssetService assetService;

    @InjectMocks
    private AssetController assetController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(assetController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllAssets() throws Exception {
        List<Asset> assets = Arrays.asList(
                new Asset(1L, "Gold", "commodity", 150.5),
                new Asset(2L, "Silver", "commodity",  25.0)
        );

        when(assetService.getAllAssets()).thenReturn(assets);

        mockMvc.perform(get("/assets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Gold"))
                .andExpect(jsonPath("$[1].name").value("Silver"));

        verify(assetService, times(1)).getAllAssets();
    }

    @Test
    void testGetAssetById() throws Exception {
        Asset asset = new Asset(1L, "Gold", "commodity", 150.5);

        when(assetService.getAssetById(1L)).thenReturn(asset);

        mockMvc.perform(get("/assets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gold"))
                .andExpect(jsonPath("$.type").value("commodity"))
                .andExpect(jsonPath("$.quantity").value(150.5));

        verify(assetService, times(1)).getAssetById(1L);
    }

    @Test
    void testGetAssetsByName() throws Exception {
        List<Asset> assets = List.of(new Asset(1L, "Gold", "commodity", 150.5));

        when(assetService.getAssetsByName("gold")).thenReturn(assets);

        mockMvc.perform(get("/assets").param("name", "gold"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Gold"));

        verify(assetService, times(1)).getAssetsByName("gold");
    }

    @Test
    void testCreateAsset() throws Exception {
        AssetRequest request = new AssetRequest();
        request.setName("Gold");
        request.setType("commodity");
        request.setQuantity(150.5);

        Asset savedAsset = new Asset(1L, "Gold", "commodity", 150.5);

        when(assetService.createAsset(any(AssetRequest.class))).thenReturn(savedAsset);

        mockMvc.perform(post("/assets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gold"))
                .andExpect(jsonPath("$.type").value("commodity"))
                .andExpect(jsonPath("$.quantity").value(150.5));

        verify(assetService, times(1)).createAsset(any(AssetRequest.class));
    }
}
