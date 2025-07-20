package com.bvhfve.restoredjungleedge;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Minimal working version of the Restored Jungle Edge mod
 * This version has no external dependencies beyond basic Fabric
 */
public class MinimalMod implements ModInitializer {
    public static final String MOD_ID = "restoredjungleedge";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("🌿 Restored Jungle Edge Mod - Minimal Version Loaded!");
        LOGGER.info("Mod ID: {}", MOD_ID);
        LOGGER.info("This is a minimal working version for testing purposes.");
        
        // Test configuration access
        testConfiguration();
        
        LOGGER.info("✅ Minimal mod initialization complete!");
    }
    
    private void testConfiguration() {
        try {
            // Test if we can access the compiled config classes
            com.bvhfve.restoredjungleedge.config.ModConfig config = new com.bvhfve.restoredjungleedge.config.ModConfig();
            
            LOGGER.info("📊 Configuration Test:");
            LOGGER.info("  - Biome Rarity: {}", config.biomeGeneration.biomeRarity);
            LOGGER.info("  - Max Trees: {}", config.biomeFeatures.maxTreesPerChunk);
            LOGGER.info("  - Enable Ocelots: {}", config.mobSpawning.enableOcelots);
            LOGGER.info("  - Enable Parrots: {}", config.mobSpawning.enableParrots);
            
            // Test ConfigHelper
            int biomeRarity = com.bvhfve.restoredjungleedge.config.ConfigHelper.getBiomeRarity();
            LOGGER.info("  - ConfigHelper Biome Rarity: {}", biomeRarity);
            
            LOGGER.info("✅ Configuration system is working!");
            
        } catch (Exception e) {
            LOGGER.error("❌ Configuration test failed: {}", e.getMessage());
        }
    }
}