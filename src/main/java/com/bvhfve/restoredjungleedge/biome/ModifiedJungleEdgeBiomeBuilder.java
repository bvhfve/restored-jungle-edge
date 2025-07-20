package com.bvhfve.restoredjungleedge.biome;

import com.bvhfve.restoredjungleedge.api.ModBiomes;
import com.bvhfve.restoredjungleedge.RestoredJungleEdgeClean;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils;

import java.util.function.Consumer;

/**
 * Professional biome builder for Modified Jungle Edge variants
 * Following BiomesOPlenty patterns for comprehensive biome placement
 */
public class ModifiedJungleEdgeBiomeBuilder {
    
    /**
     * Add all Modified Jungle Edge biomes with proper climate parameters
     */
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        RestoredJungleEdgeClean.LOGGER.info("Adding Modified Jungle Edge biomes through professional biome builder");
        
        try {
            // Get configuration for biome weights
            int baseRarity = getConfiguredRarity();
            
            // Primary Modified Jungle Edge - Main biome
            addPrimaryModifiedJungleEdge(mapper, baseRarity);
            
            // Hills variant - Elevated terrain
            addModifiedJungleEdgeHills(mapper, baseRarity / 2);
            
            // Plateau variant - Flat elevated areas
            addModifiedJungleEdgePlateau(mapper, baseRarity / 3);
            
            RestoredJungleEdgeClean.LOGGER.info("Successfully added {} Modified Jungle Edge biome variants", 
                ModBiomes.getOverworldBiomes().size());
                
        } catch (Exception e) {
            RestoredJungleEdgeClean.LOGGER.error("Failed to add biomes through biome builder: {}", e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * Add primary Modified Jungle Edge biome
     */
    private void addPrimaryModifiedJungleEdge(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper, int weight) {
        // Primary spawn conditions - jungle-like but sparser
        addBiome(mapper,
            ParameterUtils.Temperature.HOT,
            ParameterUtils.Humidity.HUMID,
            ParameterUtils.Continentalness.MID_INLAND,
            ParameterUtils.Erosion.EROSION_2,
            ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
            ParameterUtils.Depth.SURFACE,
            0.0f,
            ModBiomes.MODIFIED_JUNGLE_EDGE);
            
        // Secondary spawn conditions - transition zones
        addBiome(mapper,
            ParameterUtils.Temperature.HOT,
            ParameterUtils.Humidity.HUMID,
            ParameterUtils.Continentalness.NEAR_INLAND,
            ParameterUtils.Erosion.EROSION_1,
            ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING,
            ParameterUtils.Depth.SURFACE,
            0.1f,
            ModBiomes.MODIFIED_JUNGLE_EDGE);
    }
    
    /**
     * Add Modified Jungle Edge Hills variant
     */
    private void addModifiedJungleEdgeHills(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper, int weight) {
        // Hills spawn in elevated areas with higher erosion
        addBiome(mapper,
            ParameterUtils.Temperature.HOT,
            ParameterUtils.Humidity.HUMID,
            ParameterUtils.Continentalness.MID_INLAND,
            ParameterUtils.Erosion.EROSION_1,
            ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING,
            ParameterUtils.Depth.SURFACE,
            0.2f,
            ModBiomes.MODIFIED_JUNGLE_EDGE_HILLS);
    }
    
    /**
     * Add Modified Jungle Edge Plateau variant
     */
    private void addModifiedJungleEdgePlateau(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper, int weight) {
        // Plateau spawn in flat elevated areas
        addBiome(mapper,
            ParameterUtils.Temperature.HOT,
            ParameterUtils.Humidity.HUMID,
            ParameterUtils.Continentalness.FAR_INLAND,
            ParameterUtils.Erosion.EROSION_0,
            ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING,
            ParameterUtils.Depth.SURFACE,
            0.3f,
            ModBiomes.MODIFIED_JUNGLE_EDGE_PLATEAU);
    }
    
    /**
     * Helper method to add a biome with parameters
     */
    private void addBiome(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper,
                         ParameterUtils.Temperature temperature,
                         ParameterUtils.Humidity humidity,
                         ParameterUtils.Continentalness continentalness,
                         ParameterUtils.Erosion erosion,
                         ParameterUtils.Weirdness weirdness,
                         ParameterUtils.Depth depth,
                         float offset,
                         RegistryKey<Biome> biome) {
        
        // Convert TerraBlender parameters to Fabric format
        MultiNoiseUtil.NoiseHypercube parameters = MultiNoiseUtil.createNoiseHypercube(
            temperature.parameter(),
            humidity.parameter(),
            continentalness.parameter(),
            erosion.parameter(),
            depth.parameter(),
            weirdness.parameter(),
            offset
        );
        
        mapper.accept(Pair.of(parameters, biome));
    }
    
    /**
     * Get configured rarity with safe fallback
     */
    private int getConfiguredRarity() {
        try {
            return com.bvhfve.restoredjungleedge.config.ConfigHelper.getBiomeRarity();
        } catch (Exception e) {
            RestoredJungleEdgeClean.LOGGER.debug("Failed to get rarity config, using default: {}", e.getMessage());
            return 10; // Default 10% rarity
        }
    }
}