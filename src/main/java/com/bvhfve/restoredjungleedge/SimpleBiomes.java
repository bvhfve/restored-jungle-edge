package com.bvhfve.restoredjungleedge;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

/**
 * Simplified biome creation without complex registry dependencies
 */
public class SimpleBiomes {
    
    /**
     * Create a simplified Modified Jungle Edge biome
     * This version doesn't require complex registry lookups
     */
    public static Biome createSimpleModifiedJungleEdge() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        
        // Add basic jungle edge spawns
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 8, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
        
        // Jungle-specific creatures with default weights
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.OCELOT, 2, 1, 3));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PARROT, 40, 1, 2));
        
        // Hostile mobs
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
        
        // Ambient
        spawnBuilder.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 8, 8));
        
        // Simple generation settings (empty for now)
        GenerationSettings.Builder generationBuilder = new GenerationSettings.Builder();
        
        return new Biome.Builder()
            .precipitation(true)
            .downfall(0.8f)
            .temperature(0.95f)
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
    }
    
    /**
     * Get configuration values with safe defaults
     */
    public static class SimpleConfig {
        public static int getBiomeRarity() { return 5; }
        public static int getMaxTreesPerChunk() { return 2; }
        public static int getMinTreesPerChunk() { return 0; }
        public static int getVegetationDensity() { return 4; }
        public static boolean enableOcelots() { return true; }
        public static boolean enableParrots() { return true; }
        public static int getOcelotSpawnWeight() { return 2; }
        public static int getParrotSpawnWeight() { return 40; }
    }
}