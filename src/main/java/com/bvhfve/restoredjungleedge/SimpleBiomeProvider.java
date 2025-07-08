package com.bvhfve.restoredjungleedge;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * Simplified biome provider that doesn't require complex registry operations
 */
public class SimpleBiomeProvider extends FabricDynamicRegistryProvider {
    
    public SimpleBiomeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    
    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        RestoredJungleEdgeSimple.LOGGER.info("Configuring simple biome provider...");
        
        // Simple biome registration - just log for now
        // In a full implementation, this would add the biome to the registry
        RestoredJungleEdgeSimple.LOGGER.info("Would register biome: {}", 
            RestoredJungleEdgeSimple.MODIFIED_JUNGLE_EDGE.getValue());
        
        // Note: Actual biome addition would require proper registry setup
        // entries.add(RestoredJungleEdgeSimple.MODIFIED_JUNGLE_EDGE, SimpleBiomes.createSimpleModifiedJungleEdge());
    }
    
    @Override
    public String getName() {
        return "Simple Modified Jungle Edge Biome Provider";
    }
}