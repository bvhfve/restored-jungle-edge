package com.bvhfve.restoredjungleedge.terrablender;

import com.bvhfve.restoredjungleedge.RestoredJungleEdgeClean;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

/**
 * TerraBlender integration for the Restored Jungle Edge mod
 * Provides the primary biome registration method with the highest reliability
 * Following official TerraBlender pattern for Fabric integration
 */
public class TerraBlenderIntegration implements TerraBlenderApi {
    
    @Override
    public void onTerraBlenderInitialized() {
        RestoredJungleEdgeClean.LOGGER.info("Initializing TerraBlender integration for Modified Jungle Edge");
        
        try {
            // Get the configured weight for biome generation
            int configuredRarity = getConfiguredRarity();
            int regionWeight = calculateRegionWeight(configuredRarity);
            
            // Register our region with TerraBlender - ALL TerraBlender actions here
            ModifiedJungleEdgeRegion region = new ModifiedJungleEdgeRegion(regionWeight);
            Regions.register(region);
            
            RestoredJungleEdgeClean.LOGGER.info("Successfully registered Modified Jungle Edge region with TerraBlender (weight: {})", regionWeight);
            
        } catch (Exception e) {
            RestoredJungleEdgeClean.LOGGER.error("Failed to initialize TerraBlender integration: {}", e.getMessage(), e);
            // Don't throw - let fallback methods handle biome registration
        }
    }
    
    /**
     * Get the configured rarity setting with safe fallback
     */
    private int getConfiguredRarity() {
        try {
            return com.bvhfve.restoredjungleedge.config.ConfigHelper.getBiomeRarity();
        } catch (Exception e) {
            RestoredJungleEdgeClean.LOGGER.debug("Failed to get rarity config, using default: {}", e.getMessage());
            return 10; // Default 10% rarity
        }
    }
    
    /**
     * Calculate TerraBlender region weight based on configured rarity
     * @param rarity Rarity percentage (0-100)
     * @return Region weight for TerraBlender (1-10)
     */
    private int calculateRegionWeight(int rarity) {
        // Convert rarity percentage to TerraBlender weight
        // Rarity 0-10% = weight 1 (very rare)
        // Rarity 11-25% = weight 2 (rare)
        // Rarity 26-50% = weight 3-5 (uncommon)
        // Rarity 51-75% = weight 6-8 (common)
        // Rarity 76-100% = weight 9-10 (very common)
        
        if (rarity <= 10) return 1;
        else if (rarity <= 25) return 2;
        else if (rarity <= 40) return 3;
        else if (rarity <= 55) return 4;
        else if (rarity <= 70) return 5;
        else if (rarity <= 80) return 6;
        else if (rarity <= 90) return 7;
        else return Math.min(8, Math.max(1, rarity / 10)); // Cap at 8, minimum 1
    }
    
    /**
     * Check if TerraBlender integration is available and working
     */
    public static boolean isAvailable() {
        try {
            Class.forName("terrablender.api.TerraBlenderApi");
            Class.forName("terrablender.api.Regions");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}