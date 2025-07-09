package com.bvhfve.restoredjungleedge;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clean version of Restored Jungle Edge mod without problematic dependencies
 */
public class RestoredJungleEdgeClean implements ModInitializer {
    public static final String MOD_ID = "restoredjungleedge";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final RegistryKey<Biome> MODIFIED_JUNGLE_EDGE = RegistryKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(MOD_ID, "modified_jungle_edge")
    );

    @Override
    public void onInitialize() {
        LOGGER.info("🌿 Restored Jungle Edge - Clean Version Loading...");
        
        // Initialize simple configuration
        initializeConfiguration();
        
        // Log mod information
        logModInfo();
        
        LOGGER.info("✅ Restored Jungle Edge mod loaded successfully!");
    }
    
    private void initializeConfiguration() {
        com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.startTiming("ConfigInitialization");
        
        try {
            // Use the working configuration classes
            com.bvhfve.restoredjungleedge.config.ConfigManager.initialize();
            LOGGER.info("Configuration system initialized");
            
            // Enable debug logging for development
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.setVerboseLogging(true);
            com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.setEnabled(true);
            com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.setDetailedLogging(false);
            
            // Test configuration access and log full configuration
            com.bvhfve.restoredjungleedge.debug.ConfigDebugHelper.logFullConfiguration();
            com.bvhfve.restoredjungleedge.debug.ConfigDebugHelper.testConfigurationHelpers();
            
            int biomeRarity = com.bvhfve.restoredjungleedge.config.ConfigHelper.getBiomeRarity();
            int maxTrees = com.bvhfve.restoredjungleedge.config.ConfigHelper.getMaxTreesPerChunk();
            
            LOGGER.info("Configuration loaded - Biome rarity: {}, Max trees: {}", biomeRarity, maxTrees);
            
        } catch (Exception e) {
            LOGGER.warn("Configuration initialization failed, using defaults: {}", e.getMessage());
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logBiomeGenerationError(MODIFIED_JUNGLE_EDGE, e, System.nanoTime());
        } finally {
            com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.endTiming("ConfigInitialization");
        }
    }
    
    private void logModInfo() {
        LOGGER.info("Mod Information:");
        LOGGER.info("  ID: {}", MOD_ID);
        LOGGER.info("  Version: 1.0.5");
        LOGGER.info("  Target Biome: Modified Jungle Edge");
        LOGGER.info("  Biome Registry Key: {}", MODIFIED_JUNGLE_EDGE.getValue());
        LOGGER.info("  Description: Restores the removed Modified Jungle Edge biome");
        LOGGER.info("  Debug Features: Enabled");
        
        // Log performance statistics after initialization
        com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.logStatistics();
    }
}