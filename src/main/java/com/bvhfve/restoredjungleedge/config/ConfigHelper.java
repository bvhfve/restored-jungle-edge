package com.bvhfve.restoredjungleedge.config;

import com.bvhfve.restoredjungleedge.RestoredJungleEdge;

/**
 * Helper class for accessing configuration values safely
 */
public class ConfigHelper {
    
    /**
     * Get biome rarity with validation
     */
    public static int getBiomeRarity() {
        int rarity = ConfigManager.getBiomeGeneration().biomeRarity;
        return Math.max(0, Math.min(100, rarity));
    }
    
    /**
     * Get max trees per chunk with validation
     */
    public static int getMaxTreesPerChunk() {
        int max = ConfigManager.getBiomeFeatures().maxTreesPerChunk;
        int min = ConfigManager.getBiomeFeatures().minTreesPerChunk;
        return Math.max(min, Math.min(10, max));
    }
    
    /**
     * Get min trees per chunk with validation
     */
    public static int getMinTreesPerChunk() {
        int min = ConfigManager.getBiomeFeatures().minTreesPerChunk;
        int max = ConfigManager.getBiomeFeatures().maxTreesPerChunk;
        return Math.max(0, Math.min(max, min));
    }
    
    /**
     * Get vegetation density with validation
     */
    public static int getVegetationDensity() {
        int density = ConfigManager.getBiomeFeatures().vegetationDensity;
        return Math.max(0, Math.min(20, density));
    }
    
    /**
     * Get mega jungle tree chance as a float (0.0 to 1.0)
     */
    public static float getMegaJungleTreeChance() {
        int chance = ConfigManager.getBiomeFeatures().megaJungleTreeChance;
        return Math.max(0, Math.min(100, chance)) / 100.0f;
    }
    
    /**
     * Get vine growth chance as a float (0.0 to 1.0)
     */
    public static float getVineGrowthChance() {
        int chance = ConfigManager.getBiomeFeatures().vineGrowthChance;
        return Math.max(0, Math.min(100, chance)) / 100.0f;
    }
    
    /**
     * Get ocelot spawn weight with validation
     */
    public static int getOcelotSpawnWeight() {
        if (!ConfigManager.getMobSpawning().enableOcelots) {
            return 0;
        }
        int weight = ConfigManager.getMobSpawning().ocelotSpawnWeight;
        return Math.max(0, Math.min(100, weight));
    }
    
    /**
     * Get parrot spawn weight with validation
     */
    public static int getParrotSpawnWeight() {
        if (!ConfigManager.getMobSpawning().enableParrots) {
            return 0;
        }
        int weight = ConfigManager.getMobSpawning().parrotSpawnWeight;
        return Math.max(0, Math.min(100, weight));
    }
    
    /**
     * Get hostile mob spawn rate multiplier
     */
    public static float getHostileMobSpawnRate() {
        int rate = ConfigManager.getMobSpawning().hostileMobSpawnRate;
        return Math.max(50, Math.min(150, rate)) / 100.0f;
    }
    
    /**
     * Get passive mob spawn rate multiplier
     */
    public static float getPassiveMobSpawnRate() {
        int rate = ConfigManager.getMobSpawning().passiveMobSpawnRate;
        return Math.max(50, Math.min(150, rate)) / 100.0f;
    }
    
    /**
     * Log current configuration values for debugging
     */
    public static void logConfiguration() {
        RestoredJungleEdge.LOGGER.info("=== Restored Jungle Edge Configuration ===");
        RestoredJungleEdge.LOGGER.info("Biome Rarity: {}", getBiomeRarity());
        RestoredJungleEdge.LOGGER.info("Trees per chunk: {}-{}", getMinTreesPerChunk(), getMaxTreesPerChunk());
        RestoredJungleEdge.LOGGER.info("Vegetation Density: {}", getVegetationDensity());
        RestoredJungleEdge.LOGGER.info("Mega Tree Chance: {}%", (int)(getMegaJungleTreeChance() * 100));
        RestoredJungleEdge.LOGGER.info("Vine Growth Chance: {}%", (int)(getVineGrowthChance() * 100));
        RestoredJungleEdge.LOGGER.info("Ocelots Enabled: {}, Weight: {}", 
            ConfigManager.getMobSpawning().enableOcelots, getOcelotSpawnWeight());
        RestoredJungleEdge.LOGGER.info("Parrots Enabled: {}, Weight: {}", 
            ConfigManager.getMobSpawning().enableParrots, getParrotSpawnWeight());
        RestoredJungleEdge.LOGGER.info("==========================================");
    }
}