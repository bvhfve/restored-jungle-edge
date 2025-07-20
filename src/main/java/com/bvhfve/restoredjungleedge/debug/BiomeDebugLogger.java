package com.bvhfve.restoredjungleedge.debug;

import com.bvhfve.restoredjungleedge.RestoredJungleEdge;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Specialized logger for biome generation debugging
 */
public class BiomeDebugLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger("RestoredJungleEdge-BiomeDebug");
    
    // Generation statistics
    private static final AtomicInteger biomesGenerated = new AtomicInteger(0);
    private static final AtomicInteger chunksProcessed = new AtomicInteger(0);
    private static final AtomicLong totalGenerationTime = new AtomicLong(0);
    private static final AtomicInteger errors = new AtomicInteger(0);
    
    // Debug flags
    private static boolean verboseLogging = false;
    private static boolean performanceLogging = true;
    private static boolean errorLogging = true;
    
    /**
     * Enable or disable verbose logging
     */
    public static void setVerboseLogging(boolean enabled) {
        verboseLogging = enabled;
        LOGGER.info("Verbose biome logging {}", enabled ? "ENABLED" : "DISABLED");
    }
    
    /**
     * Enable or disable performance logging
     */
    public static void setPerformanceLogging(boolean enabled) {
        performanceLogging = enabled;
        LOGGER.info("Performance biome logging {}", enabled ? "ENABLED" : "DISABLED");
    }
    
    /**
     * Log biome generation start
     */
    public static long logBiomeGenerationStart(RegistryKey<Biome> biomeKey) {
        long startTime = System.nanoTime();
        
        if (verboseLogging) {
            LOGGER.info("🌿 Starting biome generation for: {}", biomeKey.getValue());
            LOGGER.debug("  - Generation attempt #{}", biomesGenerated.get() + 1);
            LOGGER.debug("  - Thread: {}", Thread.currentThread().getName());
        }
        
        return startTime;
    }
    
    /**
     * Log successful biome generation
     */
    public static void logBiomeGenerationSuccess(RegistryKey<Biome> biomeKey, long startTime) {
        long duration = System.nanoTime() - startTime;
        biomesGenerated.incrementAndGet();
        totalGenerationTime.addAndGet(duration);
        
        if (verboseLogging) {
            LOGGER.info("✅ Successfully generated biome: {}", biomeKey.getValue());
            if (performanceLogging) {
                LOGGER.debug("  - Generation time: {:.2f}ms", duration / 1_000_000.0);
            }
        }
        
        // Log milestone achievements
        int count = biomesGenerated.get();
        if (count % 100 == 0) {
            LOGGER.info("🎯 Milestone: {} biomes generated successfully", count);
            logStatistics();
        }
    }
    
    /**
     * Log biome generation error
     */
    public static void logBiomeGenerationError(RegistryKey<Biome> biomeKey, Exception error, long startTime) {
        long duration = System.nanoTime() - startTime;
        errors.incrementAndGet();
        
        if (errorLogging) {
            LOGGER.error("❌ Failed to generate biome: {}", biomeKey.getValue());
            LOGGER.error("  - Error: {}", error.getMessage());
            LOGGER.error("  - Duration before failure: {:.2f}ms", duration / 1_000_000.0);
            LOGGER.debug("  - Stack trace:", error);
        }
    }
    
    /**
     * Log chunk processing
     */
    public static void logChunkProcessing(int chunkX, int chunkZ, String phase) {
        chunksProcessed.incrementAndGet();
        
        if (verboseLogging) {
            LOGGER.debug("🗺️ Processing chunk [{}, {}] - Phase: {}", chunkX, chunkZ, phase);
        }
        
        // Log chunk processing milestones
        int count = chunksProcessed.get();
        if (count % 1000 == 0) {
            LOGGER.info("📊 Processed {} chunks", count);
        }
    }
    
    /**
     * Log biome feature addition
     */
    public static void logFeatureAddition(String featureType, String featureName) {
        if (verboseLogging) {
            LOGGER.debug("🌳 Adding {} feature: {}", featureType, featureName);
        }
    }
    
    /**
     * Log mob spawn configuration
     */
    public static void logMobSpawnConfig(String mobType, int weight, int minGroup, int maxGroup) {
        if (verboseLogging) {
            LOGGER.debug("🐾 Configuring mob spawn - Type: {}, Weight: {}, Group: {}-{}", 
                mobType, weight, minGroup, maxGroup);
        }
    }
    
    /**
     * Log configuration values being used
     */
    public static void logConfigurationUsage(String configKey, Object value) {
        if (verboseLogging) {
            LOGGER.debug("⚙️ Using config - {}: {}", configKey, value);
        }
    }
    
    /**
     * Log biome characteristics
     */
    public static void logBiomeCharacteristics(RegistryKey<Biome> biomeKey, float temperature, float downfall) {
        if (verboseLogging) {
            LOGGER.debug("🌡️ Biome characteristics for {} - Temperature: {:.2f}, Downfall: {:.2f}", 
                biomeKey.getValue(), temperature, downfall);
        }
    }
    
    /**
     * Log generation statistics
     */
    public static void logStatistics() {
        int generated = biomesGenerated.get();
        int processed = chunksProcessed.get();
        int errorCount = errors.get();
        long totalTime = totalGenerationTime.get();
        
        LOGGER.info("📊 BIOME GENERATION STATISTICS:");
        LOGGER.info("  - Biomes Generated: {}", generated);
        LOGGER.info("  - Chunks Processed: {}", processed);
        LOGGER.info("  - Errors Encountered: {}", errorCount);
        
        if (generated > 0 && performanceLogging) {
            double avgTime = (totalTime / 1_000_000.0) / generated;
            LOGGER.info("  - Average Generation Time: {:.2f}ms", avgTime);
            LOGGER.info("  - Total Generation Time: {:.2f}ms", totalTime / 1_000_000.0);
            LOGGER.info("  - Success Rate: {:.1f}%", (generated * 100.0) / (generated + errorCount));
        }
    }
    
    /**
     * Reset all statistics
     */
    public static void resetStatistics() {
        biomesGenerated.set(0);
        chunksProcessed.set(0);
        totalGenerationTime.set(0);
        errors.set(0);
        LOGGER.info("🔄 Biome generation statistics reset");
    }
    
    /**
     * Get current statistics as a formatted string
     */
    public static String getStatisticsString() {
        return String.format("Generated: %d, Chunks: %d, Errors: %d, Avg Time: %.2fms",
            biomesGenerated.get(),
            chunksProcessed.get(),
            errors.get(),
            biomesGenerated.get() > 0 ? (totalGenerationTime.get() / 1_000_000.0) / biomesGenerated.get() : 0.0
        );
    }
}