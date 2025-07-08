package com.bvhfve.restoredjungleedge;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
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
    }
    
    public static Biome createModifiedJungleEdge(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        
        // Add typical jungle edge spawns but with reduced rates (modified characteristic)
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 8, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.OCELOT, 2, 1, 3));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PARROT, 40, 1, 2));
        
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
        
        GenerationSettings.LookupBackedBuilder generationBuilder = new GenerationSettings.LookupBackedBuilder(
            context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
            context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        );
        
        // Add basic world generation features
        ModBiomeFeatures.addBasicFeatures(generationBuilder);
        ModBiomeFeatures.addModifiedJungleEdgeFeatures(generationBuilder);
        
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