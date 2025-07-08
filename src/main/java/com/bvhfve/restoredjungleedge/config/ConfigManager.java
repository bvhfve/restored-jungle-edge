package com.bvhfve.restoredjungleedge.config;

import com.bvhfve.restoredjungleedge.RestoredJungleEdge;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class ConfigManager {
    private static ModConfig config;
    
    public static void initialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        
        RestoredJungleEdge.LOGGER.info("Loaded Restored Jungle Edge configuration");
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
        AutoConfig.getConfigHolder(ModConfig.class).save();
    }
}