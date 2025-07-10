package com.bvhfve.restoredjungleedge;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

public class ModBiomes {
    
    public static void registerBiomes() {
        // Biome registration is handled through datagen
        RestoredJungleEdgeClean.LOGGER.info("Biome registration will be handled through datagen");
    }
    
    /**
     * Bootstrap method for datagen biome registration
     * Re-enabled with safe fallback approach
     */
    public static void bootstrap(net.minecraft.registry.Registerable<Biome> biomeRegisterable) {
        RestoredJungleEdgeClean.LOGGER.info("Attempting safe biome registration for Modified Jungle Edge");
        
        try {
            // Create a simple, safe biome that should not cause parsing issues
            Biome modifiedJungleEdge = createSimpleModifiedJungleEdge();
            biomeRegisterable.register(RestoredJungleEdgeClean.MODIFIED_JUNGLE_EDGE, modifiedJungleEdge);
            
            RestoredJungleEdgeClean.LOGGER.info("Successfully registered Modified Jungle Edge biome - now locatable with /locate biome");
            
        } catch (Exception e) {
            RestoredJungleEdgeClean.LOGGER.error("Failed to register biome, falling back to API-only approach: {}", e.getMessage());
            // Don't throw the exception - let the mod continue with API-only functionality
        }
    }
    
    /**
     * Creates a simple version of Modified Jungle Edge biome for compilation
     */
    public static Biome createSimpleModifiedJungleEdge() {
        long startTime = com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logBiomeGenerationStart(RestoredJungleEdgeClean.MODIFIED_JUNGLE_EDGE);
        com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.startTiming("BiomeCreation");
        
        try {
            SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        
            // Add typical jungle edge spawns but with configurable rates
            spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
            spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 4, 4));
            spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 8, 4, 4));
            spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
            
            // Use configuration for ocelot and parrot spawning
            try {
                int ocelotWeight = com.bvhfve.restoredjungleedge.config.ConfigHelper.getOcelotSpawnWeight();
                int parrotWeight = com.bvhfve.restoredjungleedge.config.ConfigHelper.getParrotSpawnWeight();
                
                if (ocelotWeight > 0) {
                    spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.OCELOT, ocelotWeight, 1, 3));
                }
                if (parrotWeight > 0) {
                    spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PARROT, parrotWeight, 1, 2));
                }
            } catch (Exception e) {
                // Fallback to default values if config is not available
                spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.OCELOT, 2, 1, 3));
                spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PARROT, 40, 1, 2));
            }
            
            // Hostile mobs
            spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
            spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
            spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
            spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
            spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
            spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 4, 4));
            spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
            spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
            
            // Ambient
            spawnBuilder.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 8, 8));
            
            // Simple generation settings
            GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
            
            // Log biome characteristics
            float temperature = 0.95f;
            float downfall = 0.8f;
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logBiomeCharacteristics(RestoredJungleEdgeClean.MODIFIED_JUNGLE_EDGE, temperature, downfall);
            
            Biome biome = new Biome.Builder()
                .precipitation(true)
                .downfall(downfall)
                .temperature(temperature)
                .generationSettings(generationBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects(new BiomeEffects.Builder()
                    .waterColor(0x3F76E4)
                    .waterFogColor(0x050533)
                    .skyColor(0x77ADFF)
                    .grassColor(0x59C93C)
                    .foliageColor(0x30BB0B)
                    .fogColor(0xC0D8FF)
                    .moodSound(BiomeMoodSound.CAVE)
                    .build())
                .build();
                
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logBiomeGenerationSuccess(RestoredJungleEdgeClean.MODIFIED_JUNGLE_EDGE, startTime);
            com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.endTiming("BiomeCreation");
            
            return biome;
            
        } catch (Exception e) {
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logBiomeGenerationError(RestoredJungleEdgeClean.MODIFIED_JUNGLE_EDGE, e, startTime);
            com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.endTiming("BiomeCreation");
            throw e;
        }
    }
    
    public static Biome createModifiedJungleEdge(RegistryWrapper.WrapperLookup registries) {
        long startTime = com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logBiomeGenerationStart(RestoredJungleEdge.MODIFIED_JUNGLE_EDGE);
        com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.startTiming("BiomeCreation");
        
        try {
            SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        
        // Add typical jungle edge spawns but with configurable rates
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 8, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
        
        // Use configuration for ocelot and parrot spawning
        try {
            int ocelotWeight = com.bvhfve.restoredjungleedge.config.ConfigHelper.getOcelotSpawnWeight();
            int parrotWeight = com.bvhfve.restoredjungleedge.config.ConfigHelper.getParrotSpawnWeight();
            
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logConfigurationUsage("ocelotSpawnWeight", ocelotWeight);
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logConfigurationUsage("parrotSpawnWeight", parrotWeight);
            
            if (ocelotWeight > 0) {
                spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.OCELOT, ocelotWeight, 1, 3));
                com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logMobSpawnConfig("OCELOT", ocelotWeight, 1, 3);
            }
            if (parrotWeight > 0) {
                spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PARROT, parrotWeight, 1, 2));
                com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logMobSpawnConfig("PARROT", parrotWeight, 1, 2);
            }
        } catch (Exception e) {
            // Fallback to default values if config is not available
            RestoredJungleEdge.LOGGER.warn("Failed to load mob spawn config, using defaults: {}", e.getMessage());
            spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.OCELOT, 2, 1, 3));
            spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PARROT, 40, 1, 2));
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logMobSpawnConfig("OCELOT", 2, 1, 3);
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logMobSpawnConfig("PARROT", 40, 1, 2);
        }
        
        // Hostile mobs
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
        
        // Ambient
        spawnBuilder.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 8, 8));
        
        // Simplified generation settings without complex registry lookups
        GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
        
        // Add basic world generation features
        ModBiomeFeatures.addBasicFeatures(generationBuilder);
        ModBiomeFeatures.addModifiedJungleEdgeFeatures(generationBuilder);
        
        // Log biome characteristics
        float temperature = 0.95f;
        float downfall = 0.8f;
        com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logBiomeCharacteristics(RestoredJungleEdge.MODIFIED_JUNGLE_EDGE, temperature, downfall);
        
        Biome biome = new Biome.Builder()
            .precipitation(true)
            .downfall(downfall)
            .temperature(temperature)
            .generationSettings(generationBuilder.build())
            .spawnSettings(spawnBuilder.build())
            .effects(new BiomeEffects.Builder()
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .skyColor(0x77ADFF)
                .grassColor(0x59C93C)
                .foliageColor(0x30BB0B)
                .fogColor(0xC0D8FF)
                .moodSound(BiomeMoodSound.CAVE)
                .build())
            .build();
            
        com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logBiomeGenerationSuccess(RestoredJungleEdge.MODIFIED_JUNGLE_EDGE, startTime);
        com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.endTiming("BiomeCreation");
        
        return biome;
        
        } catch (Exception e) {
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logBiomeGenerationError(RestoredJungleEdge.MODIFIED_JUNGLE_EDGE, e, startTime);
            com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.endTiming("BiomeCreation");
            throw e;
        }
    }
    
    public static MultiNoiseUtil.NoiseHypercube getModifiedJungleEdgeNoisePoint() {
        // Similar to jungle edge but with slight modifications to make it rarer
        return MultiNoiseUtil.createNoiseHypercube(
            0.1f,    // temperature
            0.3f,    // humidity 
            0.0f,    // continentalness
            0.2f,    // erosion
            0.0f,    // depth
            0.0f,    // weirdness
            0L       // offset
        );
    }
}