package com.bvhfve.restoredjungleedge;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * Simplified data generation without complex registry dependencies
 */
public class SimpleDataGeneration implements DataGeneratorEntrypoint {
    
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        RestoredJungleEdgeSimple.LOGGER.info("Initializing simple data generation...");
        
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        
        // Add simple biome provider
        pack.addProvider(SimpleBiomeProvider::new);
        
        RestoredJungleEdgeSimple.LOGGER.info("Simple data generation initialized");
    }
}