package com.bvhfve.restoredjungleedge.datagen;

import com.bvhfve.restoredjungleedge.ModBiomes;
import com.bvhfve.restoredjungleedge.RestoredJungleEdgeClean;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ModDataGeneration implements DataGeneratorEntrypoint {
    
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        RestoredJungleEdgeClean.LOGGER.info("Initializing data generation for Restored Jungle Edge");
        
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        
        // Register biome provider for datagen
        pack.addProvider(ModBiomeProvider::new);
        
        RestoredJungleEdgeClean.LOGGER.info("Data generation providers registered successfully");
    }
    
    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        // Re-enabling biome registration with minimal, safe JSON structure
        // Using simplified approach to minimize crash risks
        try {
            registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
            RestoredJungleEdgeClean.LOGGER.info("Registry builder configured for safe biome registration");
        } catch (Exception e) {
            RestoredJungleEdgeClean.LOGGER.error("Failed to configure registry builder: {}", e.getMessage());
            // Continue without registry registration if it fails
        }
    }
}