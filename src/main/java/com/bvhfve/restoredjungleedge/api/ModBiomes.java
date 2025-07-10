package com.bvhfve.restoredjungleedge.api;

import com.bvhfve.restoredjungleedge.RestoredJungleEdgeClean;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.List;

/**
 * Professional biome registry following BiomesOPlenty patterns
 * Provides centralized biome management and variant support
 */
public class ModBiomes {
    private static List<RegistryKey<Biome>> overworldBiomes = Lists.newArrayList();
    private static List<RegistryKey<Biome>> allBiomes = Lists.newArrayList();
    
    // Primary Modified Jungle Edge biome
    public static final RegistryKey<Biome> MODIFIED_JUNGLE_EDGE = registerOverworld("modified_jungle_edge");
    
    // Biome variants for enhanced variety
    public static final RegistryKey<Biome> MODIFIED_JUNGLE_EDGE_HILLS = registerOverworld("modified_jungle_edge_hills");
    public static final RegistryKey<Biome> MODIFIED_JUNGLE_EDGE_PLATEAU = registerOverworld("modified_jungle_edge_plateau");
    
    /**
     * Get all overworld biomes registered by this mod
     */
    public static List<RegistryKey<Biome>> getOverworldBiomes() {
        return ImmutableList.copyOf(overworldBiomes);
    }
    
    /**
     * Get all biomes registered by this mod
     */
    public static List<RegistryKey<Biome>> getAllBiomes() {
        return ImmutableList.copyOf(allBiomes);
    }
    
    /**
     * Register an overworld biome
     */
    private static RegistryKey<Biome> registerOverworld(String name) {
        RegistryKey<Biome> key = RegistryKey.of(RegistryKeys.BIOME, 
            Identifier.of(RestoredJungleEdgeClean.MOD_ID, name));
        overworldBiomes.add(key);
        allBiomes.add(key);
        return key;
    }
    
    /**
     * Register a non-overworld biome (for future expansion)
     */
    private static RegistryKey<Biome> register(String name) {
        RegistryKey<Biome> key = RegistryKey.of(RegistryKeys.BIOME, 
            Identifier.of(RestoredJungleEdgeClean.MOD_ID, name));
        allBiomes.add(key);
        return key;
    }
    
    /**
     * Setup method called during mod initialization
     */
    public static void setup() {
        RestoredJungleEdgeClean.LOGGER.info("Setting up ModBiomes registry with {} overworld biomes", 
            overworldBiomes.size());
    }
    
    /**
     * TerraBlender-specific setup called when TerraBlender is available
     */
    public static void setupTerraBlender() {
        RestoredJungleEdgeClean.LOGGER.info("Setting up TerraBlender integration for {} biomes", 
            overworldBiomes.size());
    }
}