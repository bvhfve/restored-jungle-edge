package com.bvhfve.restoredjungleedge;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clean version of Restored Jungle Edge mod with multi-layer biome registration
 * TerraBlender integration handled by dedicated TerraBlenderIntegration class
 */
public class RestoredJungleEdgeClean implements ModInitializer {
    public static final String MOD_ID = "restoredjungleedge";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Use the professional biome registry (will be initialized after ModBiomes.setup())
    public static RegistryKey<Biome> MODIFIED_JUNGLE_EDGE;

    @Override
    public void onInitialize() {
        LOGGER.info("Restored Jungle Edge v1.0.7 - Multi-Layer Biome Registration Loading...");
        
        // Initialize professional biome registry
        com.bvhfve.restoredjungleedge.api.ModBiomes.setup();
        
        // Initialize the main biome reference
        MODIFIED_JUNGLE_EDGE = com.bvhfve.restoredjungleedge.api.ModBiomes.MODIFIED_JUNGLE_EDGE;
        
        // Initialize simple configuration
        initializeConfiguration();
        
        // Initialize fallback biome registration system
        initializeFallbackBiomeRegistration();
        
        // Log mod information
        logModInfo();
        
        LOGGER.info("Restored Jungle Edge mod loaded successfully!");
    }
    
    private void initializeConfiguration() {
        com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.startTiming("ConfigInitialization");
        
        try {
            // Use the working configuration classes
            com.bvhfve.restoredjungleedge.config.ModConfig.initialize();
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
        LOGGER.info("  Version: 1.0.7");
        LOGGER.info("  Target Biome: Modified Jungle Edge");
        LOGGER.info("  Biome Registry Key: {}", MODIFIED_JUNGLE_EDGE.getValue());
        LOGGER.info("  Description: Restores the removed Modified Jungle Edge biome");
        LOGGER.info("  Registration Strategy: Multi-layer (TerraBlender -> Datagen -> BiomeModifications)");
        LOGGER.info("  TerraBlender Integration: Handled by dedicated TerraBlenderIntegration class");
        LOGGER.info("  Debug Features: Enabled");
        
        // Test and log configuration system
        try {
            com.bvhfve.restoredjungleedge.config.ConfigHelper.logConfiguration();
        } catch (Exception e) {
            LOGGER.error("Failed to log configuration: {}", e.getMessage(), e);
        }
        
        // Log performance statistics after initialization
        com.bvhfve.restoredjungleedge.debug.PerformanceProfiler.logStatistics();
    }
    
    /**
     * Initialize the fallback biome registration system
     * TerraBlender registration is handled separately via TerraBlenderIntegration class
     */
    private void initializeFallbackBiomeRegistration() {
        LOGGER.info("Initializing fallback biome registration system...");
        
        // Note: TerraBlender (Primary) is handled by dedicated TerraBlenderIntegration class
        // Skip TerraBlender availability check during main initialization to avoid conflicts
        LOGGER.info("  TerraBlender integration will be handled by dedicated TerraBlenderIntegration class");
        
        // Layer 2: Datagen (Fallback) - Always attempt
        try {
            ModBiomes.registerBiomes();
            LOGGER.info("  Datagen registration initialized");
        } catch (Exception e) {
            LOGGER.warn("  Datagen registration failed: {}", e.getMessage());
        }
        
        // Layer 3: BiomeModifications API (Backup) - Always works
        try {
            ModBiomeGeneration.registerBiomeGeneration();
            LOGGER.info("  BiomeModifications API registered (backup always available)");
        } catch (Exception e) {
            LOGGER.error("  BiomeModifications API failed: {}", e.getMessage(), e);
        }
        
        LOGGER.info("Fallback biome registration system initialized");
    }
    
    /**
     * Check if TerraBlender is available (safe method that doesn't trigger class initialization)
     */
    private boolean isTerraBlenderAvailable() {
        try {
            // Only check if classes exist without initializing them
            Class.forName("terrablender.api.TerraBlenderApi", false, this.getClass().getClassLoader());
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError | ExceptionInInitializerError e) {
            return false;
        }
    }
}