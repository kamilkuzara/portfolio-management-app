package com.neueda.portfolio_management.entity;

import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

public class AssetTest {

    @Test
    public void asset_isAnnotatedAsEntity() {
        // verifies that Asset is a JPA entity (matches Transaction expectations)
        assertTrue(Asset.class.isAnnotationPresent(Entity.class),
                "Asset should be annotated with @Entity");
    }

    @Test
    public void asset_hasPublicNoArgConstructor_and_canInstantiate() throws Exception {
        // ensure new Asset() used by tests/controllers won't fail
        Constructor<Asset> ctor = Asset.class.getDeclaredConstructor();
        assertNotNull(ctor, "Asset should have a no-arg constructor");
        ctor.setAccessible(true);
        Asset a = ctor.newInstance();
        assertNotNull(a);
    }

    @Test
    public void toString_doesNotThrow() throws Exception {
        // defensive: ensure serialization/logging in tests doesn't NPE or throw
        Asset a = Asset.class.getDeclaredConstructor().newInstance();
        String s = a.toString();
        assertNotNull(s);
    }

    @Test
    public void constructorWithFields_andGetters_work() throws Exception {
        // other tests in the suite construct Asset(1L, "Name", "Type", "Quantity"), assert that API
        Constructor<Asset> ctor = Asset.class.getDeclaredConstructor(Long.class, String.class, String.class, Double.class);
        assertNotNull(ctor, "Asset should have a (Long, String, String, Double) constructor");
        ctor.setAccessible(true);
        Asset asset = ctor.newInstance(1L, "Gold", "commodity", 150.5);
        assertNotNull(asset);
        // common getters used across service/controller tests
        assertEquals(1L, asset.getId());
        assertEquals("Gold", asset.getName());
        assertEquals("commodity", asset.getType());
        assertEquals(150.5, asset.getQuantity());
    }

    @Test
    public void setters_updateValues() throws Exception {
        Asset asset = Asset.class.getDeclaredConstructor().newInstance();
        asset.setId(42L);
        asset.setName("Silver");
        asset.setType("commodity");
        asset.setQuantity(150.5);
        assertEquals(42L, asset.getId());
        assertEquals("Silver", asset.getName());
        assertEquals("commodity", asset.getType());
        assertEquals(150.5, asset.getQuantity());
    }
}
