package com.bvhfve.restoredjungleedge.config;

import com.bvhfve.restoredjungleedge.RestoredJungleEdge;

public class ConfigManager {
    private static ModConfig config;
    
    public static void initialize() {
        // Temporarily use default config for testing
        config = new ModConfig();
        
        RestoredJungleEdge.LOGGER.info("Loaded Restored Jungle Edge configuration (default)");
    }
    
    public static ModConfig getConfig() {
        return config;
    }
    
    public static ModConfig.BiomeGenerationConfig getBiomeGeneration() {
        return config.biomeGeneration;
    }
    
    public static ModConfig.BiomeFeaturesConfig getBiomeFeatures() {
        return config.biomeFeatures;
    }
    
    public static ModConfig.MobSpawningConfig getMobSpawning() {
        return config.mobSpawning;
    }
    
    public static void save() {
        // Save functionality would be implemented here
        // For now, using in-memory configuration
        RestoredJungleEdge.LOGGER.info("Configuration saved (in-memory)");
    }
}