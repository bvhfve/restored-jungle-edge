package com.bvhfve.restoredjungleedge.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * Main configuration class for Restored Jungle Edge mod.
 * Contains all configuration options organized into logical categories.
 * Follows the Torcherino config pattern with singleton instance.
 */
public class ModConfig {
    public static ModConfig INSTANCE;
    
    @SerializedName("biome_generation")
    public final BiomeGenerationConfig biomeGeneration = new BiomeGenerationConfig();
    
    @SerializedName("biome_features")
    public final BiomeFeaturesConfig biomeFeatures = new BiomeFeaturesConfig();
    
    @SerializedName("mob_spawning")
    public final MobSpawningConfig mobSpawning = new MobSpawningConfig();
    
    /**
     * Configuration for biome generation and placement
     */
    public static class BiomeGenerationConfig {
        @SerializedName("biome_rarity")
        public int biomeRarity = 5;
        
        @SerializedName("temperature_variance")
        public int temperatureVariance = 10;
        
        @SerializedName("humidity_variance")
        public int humidityVariance = 15;
        
        @SerializedName("enable_in_existing_worlds")
        public boolean enableInExistingWorlds = false;
        
        @SerializedName("replace_jungle_edge")
        public boolean replaceJungleEdge = false;
        
        /**
         * Validates and clamps configuration values to acceptable ranges
         */
        public void validate() {
            biomeRarity = Math.max(0, Math.min(100, biomeRarity));
            temperatureVariance = Math.max(0, Math.min(200, temperatureVariance));
            humidityVariance = Math.max(0, Math.min(200, humidityVariance));
        }
    }
    
    /**
     * Configuration for biome features like trees, vegetation, and structures
     */
    public static class BiomeFeaturesConfig {
        @SerializedName("max_trees_per_chunk")
        public int maxTreesPerChunk = 2;
        
        @SerializedName("min_trees_per_chunk")
        public int minTreesPerChunk = 0;
        
        @SerializedName("mega_jungle_tree_chance")
        public int megaJungleTreeChance = 10;
        
        @SerializedName("vine_growth_chance")
        public int vineGrowthChance = 30;
        
        @SerializedName("vegetation_density")
        public int vegetationDensity = 4;
        
        @SerializedName("enable_cocoa_beans")
        public boolean enableCocoaBeans = true;
        
        @SerializedName("enable_melon_patches")
        public boolean enableMelonPatches = true;
        
        /**
         * Validates and clamps configuration values to acceptable ranges
         */
        public void validate() {
            maxTreesPerChunk = Math.max(0, Math.min(10, maxTreesPerChunk));
            minTreesPerChunk = Math.max(0, Math.min(10, minTreesPerChunk));
            megaJungleTreeChance = Math.max(0, Math.min(100, megaJungleTreeChance));
            vineGrowthChance = Math.max(0, Math.min(100, vineGrowthChance));
            vegetationDensity = Math.max(0, Math.min(20, vegetationDensity));
            
            // Ensure min <= max for tree counts
            if (minTreesPerChunk > maxTreesPerChunk) {
                minTreesPerChunk = maxTreesPerChunk;
            }
        }
    }
    
    /**
     * Configuration for mob spawning in the biome
     */
    public static class MobSpawningConfig {
        @SerializedName("enable_ocelots")
        public boolean enableOcelots = true;
        
        @SerializedName("enable_parrots")
        public boolean enableParrots = true;
        
        @SerializedName("ocelot_spawn_weight")
        public int ocelotSpawnWeight = 2;
        
        @SerializedName("parrot_spawn_weight")
        public int parrotSpawnWeight = 40;
        
        @SerializedName("hostile_mob_spawn_rate")
        public int hostileMobSpawnRate = 100;
        
        @SerializedName("passive_mob_spawn_rate")
        public int passiveMobSpawnRate = 100;
        
        /**
         * Validates and clamps configuration values to acceptable ranges
         */
        public void validate() {
            ocelotSpawnWeight = Math.max(0, Math.min(100, ocelotSpawnWeight));
            parrotSpawnWeight = Math.max(0, Math.min(100, parrotSpawnWeight));
            hostileMobSpawnRate = Math.max(50, Math.min(150, hostileMobSpawnRate));
            passiveMobSpawnRate = Math.max(50, Math.min(150, passiveMobSpawnRate));
        }
    }
    
    /**
     * Validates all configuration sections
     */
    public void validate() {
        if (biomeGeneration != null) {
            biomeGeneration.validate();
        }
        if (biomeFeatures != null) {
            biomeFeatures.validate();
        }
        if (mobSpawning != null) {
            mobSpawning.validate();
        }
    }
    
    /**
     * Initializes the configuration system following Torcherino pattern
     */
    public static void initialize() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        
        var configDir = FabricLoader.getInstance().getConfigDir().resolve("restored-jungle-edge.json");
        var logger = LogManager.getLogger("restored-jungle-edge-config");
        
        ModConfig config = null;
        
        try {
            Files.createDirectories(configDir.getParent());
        } catch (IOException e) {
            logger.warn("Failed to create directory required for restored-jungle-edge config, using default config.");
            config = new ModConfig();
        }
        
        if (config == null) {
            if (Files.exists(configDir)) {
                try (var reader = Files.newBufferedReader(configDir)) {
                    config = gson.fromJson(reader, ModConfig.class);
                    if (config == null) {
                        logger.warn("Config file exists but is empty or invalid, using default config.");
                        config = new ModConfig();
                    }
                } catch (IOException e) {
                    logger.warn("Failed to read restored-jungle-edge config file, using default config.");
                    config = new ModConfig();
                }
            } else {
                config = new ModConfig();
                try (var writer = Files.newBufferedWriter(configDir, StandardOpenOption.CREATE_NEW)) {
                    gson.toJson(config, writer);
                    logger.info("Created default configuration file: {}", configDir);
                } catch (IOException e) {
                    logger.warn("Failed to save default restored-jungle-edge config file.");
                }
            }
        }
        
        INSTANCE = config;
        INSTANCE.onConfigLoaded();
    }
    
    /**
     * Called after configuration is loaded to perform any post-load processing
     */
    private void onConfigLoaded() {
        // Validate configuration values
        validate();
        
        var logger = LogManager.getLogger("restored-jungle-edge-config");
        logger.info("Configuration loaded successfully");
        
        // Log configuration values for debugging
        if (logger.isDebugEnabled()) {
            logger.debug("Biome rarity: {}", biomeGeneration.biomeRarity);
            logger.debug("Temperature variance: {}", biomeGeneration.temperatureVariance);
            logger.debug("Humidity variance: {}", biomeGeneration.humidityVariance);
            logger.debug("Enable in existing worlds: {}", biomeGeneration.enableInExistingWorlds);
            logger.debug("Replace jungle edge: {}", biomeGeneration.replaceJungleEdge);
            logger.debug("Max trees per chunk: {}", biomeFeatures.maxTreesPerChunk);
            logger.debug("Min trees per chunk: {}", biomeFeatures.minTreesPerChunk);
            logger.debug("Enable ocelots: {}", mobSpawning.enableOcelots);
            logger.debug("Enable parrots: {}", mobSpawning.enableParrots);
        }
    }
}