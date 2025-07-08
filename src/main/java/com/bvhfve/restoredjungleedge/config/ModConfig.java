package com.bvhfve.restoredjungleedge.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "restoredjungleedge")
public class ModConfig implements ConfigData {
    
    @ConfigEntry.Category("biome_generation")
    @ConfigEntry.Gui.TransitiveObject
    public BiomeGenerationConfig biomeGeneration = new BiomeGenerationConfig();
    
    @ConfigEntry.Category("biome_features")
    @ConfigEntry.Gui.TransitiveObject
    public BiomeFeaturesConfig biomeFeatures = new BiomeFeaturesConfig();
    
    @ConfigEntry.Category("mob_spawning")
    @ConfigEntry.Gui.TransitiveObject
    public MobSpawningConfig mobSpawning = new MobSpawningConfig();
    
    public static class BiomeGenerationConfig {
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public int biomeRarity = 5;
        
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 200)
        public int temperatureVariance = 10;
        
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 200)
        public int humidityVariance = 15;
        
        @ConfigEntry.Gui.Tooltip
        public boolean enableInExistingWorlds = false;
        
        @ConfigEntry.Gui.Tooltip
        public boolean replaceJungleEdge = false;
    }
    
    public static class BiomeFeaturesConfig {
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 10)
        public int maxTreesPerChunk = 2;
        
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 10)
        public int minTreesPerChunk = 0;
        
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public int megaJungleTreeChance = 10;
        
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public int vineGrowthChance = 30;
        
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 20)
        public int vegetationDensity = 4;
        
        @ConfigEntry.Gui.Tooltip
        public boolean enableCocoaBeans = true;
        
        @ConfigEntry.Gui.Tooltip
        public boolean enableMelonPatches = true;
    }
    
    public static class MobSpawningConfig {
        @ConfigEntry.Gui.Tooltip
        public boolean enableOcelots = true;
        
        @ConfigEntry.Gui.Tooltip
        public boolean enableParrots = true;
        
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public int ocelotSpawnWeight = 2;
        
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public int parrotSpawnWeight = 40;
        
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 50, max = 150)
        public int hostileMobSpawnRate = 100;
        
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 50, max = 150)
        public int passiveMobSpawnRate = 100;
    }
}