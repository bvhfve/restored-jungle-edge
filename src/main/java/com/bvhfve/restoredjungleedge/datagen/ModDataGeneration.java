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
        // Biome registration disabled - using Fabric BiomeModifications API instead
        // This prevents JSON parsing issues while maintaining full functionality
        
        RestoredJungleEdgeClean.LOGGER.debug("Registry builder configured - using API-based biome modifications");
    }
}