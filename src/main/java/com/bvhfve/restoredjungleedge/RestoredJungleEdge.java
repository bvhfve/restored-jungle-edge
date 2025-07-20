package com.bvhfve.restoredjungleedge;

import com.bvhfve.restoredjungleedge.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestoredJungleEdge implements ModInitializer {
    public static final String MOD_ID = "restoredjungleedge";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final RegistryKey<Biome> MODIFIED_JUNGLE_EDGE = RegistryKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(MOD_ID, "modified_jungle_edge")
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Restoring the Modified Jungle Edge biome!");
        
        // Initialize configuration following Torcherino pattern
        ModConfig.initialize();
        
        // Register the biome
        ModBiomes.registerBiomes();
        
        // Register biome generation
        ModBiomeGeneration.registerBiomeGeneration();
        
        LOGGER.info("Modified Jungle Edge biome has been restored!");
    }
}