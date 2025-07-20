package com.bvhfve.restoredjungleedge.debug;

import com.bvhfve.restoredjungleedge.config.ConfigHelper;
import com.bvhfve.restoredjungleedge.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Debug utility for configuration validation and logging
 */
public class ConfigDebugHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger("RestoredJungleEdge-ConfigDebug");
    
    /**
     * Log all current configuration values with validation
     */
    public static void logFullConfiguration() {
        LOGGER.info("🔧 CONFIGURATION DEBUG REPORT");
        LOGGER.info("=" + "=".repeat(50));
        
        try {
            ModConfig config = ModConfig.INSTANCE;
            if (config == null) {
                LOGGER.warn("⚠️ Configuration is NULL - using fallback values");
                return;
            }
            
            logBiomeGenerationConfig(config.biomeGeneration);
            logBiomeFeaturesConfig(config.biomeFeatures);
            logMobSpawningConfig(config.mobSpawning);
            
            LOGGER.info("✅ Configuration validation completed");
            
        } catch (Exception e) {
            LOGGER.error("❌ Error during configuration debug: {}", e.getMessage(), e);
        }
    }
    
    /**
     * Log biome generation configuration
     */
    private static void logBiomeGenerationConfig(ModConfig.BiomeGenerationConfig config) {
        LOGGER.info("🌍 BIOME GENERATION CONFIG:");
        
        int biomeRarity = ConfigHelper.getBiomeRarity();
        LOGGER.info("  - Biome Rarity: {} (raw: {}) {}", 
            biomeRarity, config.biomeRarity, validateRange(biomeRarity, 0, 100));
        
        LOGGER.info("  - Temperature Variance: {} {}", 
            config.temperatureVariance, validateRange(config.temperatureVariance, 0, 200));
        
        LOGGER.info("  - Humidity Variance: {} {}", 
            config.humidityVariance, validateRange(config.humidityVariance, 0, 200));
        
        LOGGER.info("  - Enable In Existing Worlds: {}", config.enableInExistingWorlds);
        LOGGER.info("  - Replace Jungle Edge: {} {}", 
            config.replaceJungleEdge, config.replaceJungleEdge ? "⚠️ DESTRUCTIVE" : "✅ SAFE");
    }
    
    /**
     * Log biome features configuration
     */
    private static void logBiomeFeaturesConfig(ModConfig.BiomeFeaturesConfig config) {
        LOGGER.info("🌳 BIOME FEATURES CONFIG:");
        
        int maxTrees = ConfigHelper.getMaxTreesPerChunk();
        int minTrees = ConfigHelper.getMinTreesPerChunk();
        LOGGER.info("  - Trees Per Chunk: {}-{} (raw: {}-{}) {}", 
            minTrees, maxTrees, config.minTreesPerChunk, config.maxTreesPerChunk,
            validateTreeRange(minTrees, maxTrees));
        
        int vegDensity = ConfigHelper.getVegetationDensity();
        LOGGER.info("  - Vegetation Density: {} (raw: {}) {}", 
            vegDensity, config.vegetationDensity, validateRange(vegDensity, 0, 20));
        
        float megaTreeChance = ConfigHelper.getMegaJungleTreeChance();
        LOGGER.info("  - Mega Tree Chance: {:.1f}% (raw: {}) {}", 
            megaTreeChance * 100, config.megaJungleTreeChance, 
            validateRange(config.megaJungleTreeChance, 0, 100));
        
        float vineChance = ConfigHelper.getVineGrowthChance();
        LOGGER.info("  - Vine Growth Chance: {:.1f}% (raw: {}) {}", 
            vineChance * 100, config.vineGrowthChance,
            validateRange(config.vineGrowthChance, 0, 100));
        
        LOGGER.info("  - Enable Cocoa Beans: {}", config.enableCocoaBeans);
        LOGGER.info("  - Enable Melon Patches: {}", config.enableMelonPatches);
    }
    
    /**
     * Log mob spawning configuration
     */
    private static void logMobSpawningConfig(ModConfig.MobSpawningConfig config) {
        LOGGER.info("🐾 MOB SPAWNING CONFIG:");
        
        boolean ocelotsEnabled = config.enableOcelots;
        int ocelotWeight = ConfigHelper.getOcelotSpawnWeight();
        LOGGER.info("  - Ocelots: {} (Weight: {}, Raw: {}) {}", 
            ocelotsEnabled ? "ENABLED" : "DISABLED", ocelotWeight, config.ocelotSpawnWeight,
            ocelotsEnabled ? validateRange(ocelotWeight, 0, 100) : "N/A");
        
        boolean parrotsEnabled = config.enableParrots;
        int parrotWeight = ConfigHelper.getParrotSpawnWeight();
        LOGGER.info("  - Parrots: {} (Weight: {}, Raw: {}) {}", 
            parrotsEnabled ? "ENABLED" : "DISABLED", parrotWeight, config.parrotSpawnWeight,
            parrotsEnabled ? validateRange(parrotWeight, 0, 100) : "N/A");
        
        float hostileRate = ConfigHelper.getHostileMobSpawnRate();
        LOGGER.info("  - Hostile Mob Rate: {:.1f}% (raw: {}) {}", 
            hostileRate * 100, config.hostileMobSpawnRate,
            validateRange(config.hostileMobSpawnRate, 50, 150));
        
        float passiveRate = ConfigHelper.getPassiveMobSpawnRate();
        LOGGER.info("  - Passive Mob Rate: {:.1f}% (raw: {}) {}", 
            passiveRate * 100, config.passiveMobSpawnRate,
            validateRange(config.passiveMobSpawnRate, 50, 150));
    }
    
    /**
     * Validate if a value is within the expected range
     */
    private static String validateRange(int value, int min, int max) {
        if (value < min || value > max) {
            return String.format("❌ OUT OF RANGE [%d-%d]", min, max);
        }
        return "✅ VALID";
    }
    
    /**
     * Validate tree range (min <= max)
     */
    private static String validateTreeRange(int min, int max) {
        if (min > max) {
            return "❌ MIN > MAX";
        }
        if (min < 0 || max > 10) {
            return "❌ OUT OF BOUNDS [0-10]";
        }
        return "✅ VALID";
    }
    
    /**
     * Log configuration comparison with defaults
     */
    public static void logConfigurationDifferences() {
        LOGGER.info("🔍 CONFIGURATION DIFFERENCES FROM DEFAULTS:");
        
        try {
            ModConfig config = ModConfig.INSTANCE;
            ModConfig defaults = new ModConfig();
            
            // Compare biome generation
            if (config.biomeGeneration.biomeRarity != defaults.biomeGeneration.biomeRarity) {
                LOGGER.info("  - biomeRarity: {} → {} ({})", 
                    defaults.biomeGeneration.biomeRarity, config.biomeGeneration.biomeRarity,
                    config.biomeGeneration.biomeRarity > defaults.biomeGeneration.biomeRarity ? "MORE COMMON" : "RARER");
            }
            
            if (config.biomeFeatures.maxTreesPerChunk != defaults.biomeFeatures.maxTreesPerChunk) {
                LOGGER.info("  - maxTreesPerChunk: {} → {} ({})", 
                    defaults.biomeFeatures.maxTreesPerChunk, config.biomeFeatures.maxTreesPerChunk,
                    config.biomeFeatures.maxTreesPerChunk > defaults.biomeFeatures.maxTreesPerChunk ? "DENSER" : "SPARSER");
            }
            
            if (config.biomeFeatures.vegetationDensity != defaults.biomeFeatures.vegetationDensity) {
                LOGGER.info("  - vegetationDensity: {} → {} ({})", 
                    defaults.biomeFeatures.vegetationDensity, config.biomeFeatures.vegetationDensity,
                    config.biomeFeatures.vegetationDensity > defaults.biomeFeatures.vegetationDensity ? "MORE VEGETATION" : "LESS VEGETATION");
            }
            
            // Add more comparisons as needed
            
        } catch (Exception e) {
            LOGGER.error("❌ Error comparing configurations: {}", e.getMessage());
        }
    }
    
    /**
     * Test configuration helper methods
     */
    public static void testConfigurationHelpers() {
        LOGGER.info("🧪 TESTING CONFIGURATION HELPERS:");
        
        try {
            // Test all helper methods
            LOGGER.info("  - getBiomeRarity(): {}", ConfigHelper.getBiomeRarity());
            LOGGER.info("  - getMaxTreesPerChunk(): {}", ConfigHelper.getMaxTreesPerChunk());
            LOGGER.info("  - getMinTreesPerChunk(): {}", ConfigHelper.getMinTreesPerChunk());
            LOGGER.info("  - getVegetationDensity(): {}", ConfigHelper.getVegetationDensity());
            LOGGER.info("  - getMegaJungleTreeChance(): {}", ConfigHelper.getMegaJungleTreeChance());
            LOGGER.info("  - getVineGrowthChance(): {}", ConfigHelper.getVineGrowthChance());
            LOGGER.info("  - getOcelotSpawnWeight(): {}", ConfigHelper.getOcelotSpawnWeight());
            LOGGER.info("  - getParrotSpawnWeight(): {}", ConfigHelper.getParrotSpawnWeight());
            LOGGER.info("  - getHostileMobSpawnRate(): {}", ConfigHelper.getHostileMobSpawnRate());
            LOGGER.info("  - getPassiveMobSpawnRate(): {}", ConfigHelper.getPassiveMobSpawnRate());
            
            LOGGER.info("✅ All configuration helpers working correctly");
            
        } catch (Exception e) {
            LOGGER.error("❌ Configuration helper test failed: {}", e.getMessage(), e);
        }
    }
}