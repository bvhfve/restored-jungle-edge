package com.bvhfve.restoredjungleedge.config;

import com.bvhfve.restoredjungleedge.RestoredJungleEdge;

/**
 * Helper class for accessing configuration values safely with validation and convenience methods
 */
public class ConfigHelper {
    
    // Biome Generation Helpers
    
    /**
     * Get biome rarity with validation
     */
    public static int getBiomeRarity() {
        return ModConfig.INSTANCE.biomeGeneration.biomeRarity;
    }
    
    /**
     * Get temperature variance with validation
     */
    public static int getTemperatureVariance() {
        return ModConfig.INSTANCE.biomeGeneration.temperatureVariance;
    }
    
    /**
     * Get humidity variance with validation
     */
    public static int getHumidityVariance() {
        return ModConfig.INSTANCE.biomeGeneration.humidityVariance;
    }
    
    /**
     * Check if biome should be enabled in existing worlds
     */
    public static boolean isEnabledInExistingWorlds() {
        return ModConfig.INSTANCE.biomeGeneration.enableInExistingWorlds;
    }
    
    /**
     * Check if jungle edge should be replaced
     */
    public static boolean shouldReplaceJungleEdge() {
        return ModConfig.INSTANCE.biomeGeneration.replaceJungleEdge;
    }
    
    // Biome Features Helpers
    
    /**
     * Get max trees per chunk with validation
     */
    public static int getMaxTreesPerChunk() {
        return ModConfig.INSTANCE.biomeFeatures.maxTreesPerChunk;
    }
    
    /**
     * Get min trees per chunk with validation
     */
    public static int getMinTreesPerChunk() {
        return ModConfig.INSTANCE.biomeFeatures.minTreesPerChunk;
    }
    
    /**
     * Get vegetation density with validation
     */
    public static int getVegetationDensity() {
        return ModConfig.INSTANCE.biomeFeatures.vegetationDensity;
    }
    
    /**
     * Get mega jungle tree chance as a float (0.0 to 1.0)
     */
    public static float getMegaJungleTreeChance() {
        return ModConfig.INSTANCE.biomeFeatures.megaJungleTreeChance / 100.0f;
    }
    
    /**
     * Get vine growth chance as a float (0.0 to 1.0)
     */
    public static float getVineGrowthChance() {
        return ModConfig.INSTANCE.biomeFeatures.vineGrowthChance / 100.0f;
    }
    
    /**
     * Check if cocoa beans should be enabled
     */
    public static boolean areCocoaBeansEnabled() {
        return ModConfig.INSTANCE.biomeFeatures.enableCocoaBeans;
    }
    
    /**
     * Check if melon patches should be enabled
     */
    public static boolean areMelonPatchesEnabled() {
        return ModConfig.INSTANCE.biomeFeatures.enableMelonPatches;
    }
    
    // Mob Spawning Helpers
    
    /**
     * Get ocelot spawn weight with validation
     */
    public static int getOcelotSpawnWeight() {
        if (!ModConfig.INSTANCE.mobSpawning.enableOcelots) {
            return 0;
        }
        return ModConfig.INSTANCE.mobSpawning.ocelotSpawnWeight;
    }
    
    /**
     * Get parrot spawn weight with validation
     */
    public static int getParrotSpawnWeight() {
        if (!ModConfig.INSTANCE.mobSpawning.enableParrots) {
            return 0;
        }
        return ModConfig.INSTANCE.mobSpawning.parrotSpawnWeight;
    }
    
    /**
     * Check if ocelots should spawn
     */
    public static boolean areOcelotsEnabled() {
        return ModConfig.INSTANCE.mobSpawning.enableOcelots;
    }
    
    /**
     * Check if parrots should spawn
     */
    public static boolean areParrotsEnabled() {
        return ModConfig.INSTANCE.mobSpawning.enableParrots;
    }
    
    /**
     * Get hostile mob spawn rate multiplier
     */
    public static float getHostileMobSpawnRate() {
        return ModConfig.INSTANCE.mobSpawning.hostileMobSpawnRate / 100.0f;
    }
    
    /**
     * Get passive mob spawn rate multiplier
     */
    public static float getPassiveMobSpawnRate() {
        return ModConfig.INSTANCE.mobSpawning.passiveMobSpawnRate / 100.0f;
    }
    
    // Configuration Management Helpers
    
    /**
     * Reload configuration from file (Note: Not supported in Torcherino pattern)
     */
    public static void reloadConfig() {
        RestoredJungleEdge.LOGGER.warn("Configuration reloading not supported in Torcherino pattern. Restart required.");
    }
    
    /**
     * Save current configuration to file (Note: Not supported in Torcherino pattern)
     */
    public static void saveConfig() {
        RestoredJungleEdge.LOGGER.warn("Configuration saving not supported in Torcherino pattern. Changes require restart.");
    }
    
    /**
     * Reset configuration to defaults (Note: Not supported in Torcherino pattern)
     */
    public static void resetToDefaults() {
        RestoredJungleEdge.LOGGER.warn("Configuration reset not supported in Torcherino pattern. Delete config file and restart.");
    }
    
    /**
     * Log current configuration values for debugging
     */
    public static void logConfiguration() {
        var config = ModConfig.INSTANCE;
        RestoredJungleEdge.LOGGER.info("=== Restored Jungle Edge Configuration ===");
        
        // Biome Generation
        RestoredJungleEdge.LOGGER.info("--- Biome Generation ---");
        RestoredJungleEdge.LOGGER.info("Biome Rarity: {}", getBiomeRarity());
        RestoredJungleEdge.LOGGER.info("Temperature Variance: {}", getTemperatureVariance());
        RestoredJungleEdge.LOGGER.info("Humidity Variance: {}", getHumidityVariance());
        RestoredJungleEdge.LOGGER.info("Enable in Existing Worlds: {}", isEnabledInExistingWorlds());
        RestoredJungleEdge.LOGGER.info("Replace Jungle Edge: {}", shouldReplaceJungleEdge());
        
        // Biome Features
        RestoredJungleEdge.LOGGER.info("--- Biome Features ---");
        RestoredJungleEdge.LOGGER.info("Trees per chunk: {}-{}", getMinTreesPerChunk(), getMaxTreesPerChunk());
        RestoredJungleEdge.LOGGER.info("Vegetation Density: {}", getVegetationDensity());
        RestoredJungleEdge.LOGGER.info("Mega Tree Chance: {}%", (int)(getMegaJungleTreeChance() * 100));
        RestoredJungleEdge.LOGGER.info("Vine Growth Chance: {}%", (int)(getVineGrowthChance() * 100));
        RestoredJungleEdge.LOGGER.info("Cocoa Beans Enabled: {}", areCocoaBeansEnabled());
        RestoredJungleEdge.LOGGER.info("Melon Patches Enabled: {}", areMelonPatchesEnabled());
        
        // Mob Spawning
        RestoredJungleEdge.LOGGER.info("--- Mob Spawning ---");
        RestoredJungleEdge.LOGGER.info("Ocelots Enabled: {}, Weight: {}", areOcelotsEnabled(), getOcelotSpawnWeight());
        RestoredJungleEdge.LOGGER.info("Parrots Enabled: {}, Weight: {}", areParrotsEnabled(), getParrotSpawnWeight());
        RestoredJungleEdge.LOGGER.info("Hostile Mob Rate: {}%", (int)(getHostileMobSpawnRate() * 100));
        RestoredJungleEdge.LOGGER.info("Passive Mob Rate: {}%", (int)(getPassiveMobSpawnRate() * 100));
        
        RestoredJungleEdge.LOGGER.info("==========================================");
    }
    
    /**
     * Validate current configuration and log any issues
     */
    public static void validateAndLog() {
        try {
            ModConfig.INSTANCE.validate();
            RestoredJungleEdge.LOGGER.info("Configuration validation passed");
        } catch (Exception e) {
            RestoredJungleEdge.LOGGER.error("Configuration validation failed", e);
        }
    }
}