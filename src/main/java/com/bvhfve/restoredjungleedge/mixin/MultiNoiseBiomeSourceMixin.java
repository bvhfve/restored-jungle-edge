package com.bvhfve.restoredjungleedge.mixin;

import com.bvhfve.restoredjungleedge.RestoredJungleEdgeClean;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to inject Modified Jungle Edge biome generation into the MultiNoiseBiomeSource.
 * This replaces some jungle edge biomes with our modified version based on configuration.
 * 
 * NOTE: Currently disabled due to method signature issues in 1.21.4.
 * The biome injection is handled through Fabric's BiomeModifications API instead.
 */
@Mixin(MultiNoiseBiomeSource.class)
public class MultiNoiseBiomeSourceMixin {
    
    // Temporarily disabled - using BiomeModifications API instead
    // @Inject(method = "getBiome", at = @At("RETURN"), cancellable = true)
    private void injectModifiedJungleEdge_DISABLED(int x, int y, int z, MultiNoiseUtil.MultiNoiseSampler noise, CallbackInfoReturnable<RegistryEntry<Biome>> cir) {
        try {
            RegistryEntry<Biome> originalBiome = cir.getReturnValue();
            
            // Only replace jungle edge biomes if configured to do so
            if (originalBiome.matchesKey(net.minecraft.world.biome.BiomeKeys.JUNGLE)) {
                // Check if we should replace this jungle edge with modified jungle edge
                if (shouldReplaceWithModifiedJungleEdge(x, z, noise)) {
                    // Try to get our modified jungle edge biome from the registry
                    // Note: This is a simplified approach - in a full implementation,
                    // you would need proper registry access
                    RestoredJungleEdgeClean.LOGGER.debug("Replacing jungle edge at [{}, {}] with modified jungle edge", x, z);
                    
                    // For now, we'll let the biome modifications API handle the actual replacement
                    // This mixin serves as a monitoring/debugging tool
                    com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logChunkProcessing(x >> 4, z >> 4, "BiomeReplacement");
                }
            }
        } catch (Exception e) {
            // Don't break world generation if something goes wrong
            RestoredJungleEdgeClean.LOGGER.warn("Error in biome injection mixin: {}", e.getMessage());
            com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logBiomeGenerationError(
                RestoredJungleEdgeClean.MODIFIED_JUNGLE_EDGE, e, System.nanoTime()
            );
        }
    }
    
    /**
     * Determines if a jungle edge at the given coordinates should be replaced with modified jungle edge.
     * Uses configuration settings and noise-based randomization.
     */
    private boolean shouldReplaceWithModifiedJungleEdge(int x, int z, MultiNoiseUtil.MultiNoiseSampler noise) {
        try {
            // Get biome rarity from configuration (0-100, where lower = rarer)
            int biomeRarity = com.bvhfve.restoredjungleedge.config.ConfigHelper.getBiomeRarity();
            
            // Use a combination of coordinates and noise for pseudo-random distribution
            long seed = ((long) x * 341873128712L + (long) z * 132897987541L);
            java.util.Random random = new java.util.Random(seed);
            
            // Generate a random value from 0-99
            int randomValue = random.nextInt(100);
            
            // Replace if random value is less than rarity (so rarity 10 = 10% chance)
            boolean shouldReplace = randomValue < biomeRarity;
            
            if (shouldReplace) {
                try {
                    com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger.logConfigurationUsage("biomeRarity", biomeRarity);
                } catch (Exception e) {
                    // Ignore debug logging errors
                }
            }
            
            return shouldReplace;
        } catch (Exception e) {
            // Default to not replacing if config fails
            RestoredJungleEdgeClean.LOGGER.debug("Failed to check replacement conditions: {}", e.getMessage());
            return false;
        }
    }
}