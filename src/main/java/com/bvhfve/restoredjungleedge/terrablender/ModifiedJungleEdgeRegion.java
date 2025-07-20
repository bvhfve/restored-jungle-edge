package com.bvhfve.restoredjungleedge.terrablender;

import com.bvhfve.restoredjungleedge.RestoredJungleEdgeClean;
import com.bvhfve.restoredjungleedge.biome.ModifiedJungleEdgeBiomeBuilder;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

/**
 * TerraBlender region for registering the Modified Jungle Edge biome
 * This provides the most reliable biome injection method
 */
public class ModifiedJungleEdgeRegion extends Region {
    
    public ModifiedJungleEdgeRegion(int weight) {
        super(Identifier.of(RestoredJungleEdgeClean.MOD_ID, "modified_jungle_edge_region"), 
              RegionType.OVERWORLD, 
              weight);
    }
    
    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        RestoredJungleEdgeClean.LOGGER.info("Adding Modified Jungle Edge biomes through professional TerraBlender region");
        
        try {
            // Use professional biome builder following BiomesOPlenty patterns
            ModifiedJungleEdgeBiomeBuilder builder = new ModifiedJungleEdgeBiomeBuilder();
            builder.addBiomes(registry, mapper);
            
            RestoredJungleEdgeClean.LOGGER.info("Successfully added all Modified Jungle Edge biome variants to TerraBlender region");
            
        } catch (Exception e) {
            RestoredJungleEdgeClean.LOGGER.error("Failed to add biomes to TerraBlender region: {}", e.getMessage(), e);
            throw e;
        }
    }
}