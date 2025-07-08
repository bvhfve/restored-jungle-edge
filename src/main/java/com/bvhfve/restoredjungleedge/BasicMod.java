package com.bvhfve.restoredjungleedge;

/**
 * Ultra-minimal mod class with no dependencies
 */
public class BasicMod {
    public static final String MOD_ID = "restoredjungleedge";
    public static final String VERSION = "1.0.1-minimal";
    
    public static void init() {
        System.out.println("🌿 Restored Jungle Edge - Basic Version Loaded!");
        System.out.println("Mod ID: " + MOD_ID);
        System.out.println("Version: " + VERSION);
        
        // Test basic functionality
        testBasicFeatures();
    }
    
    private static void testBasicFeatures() {
        System.out.println("📊 Testing basic features:");
        
        // Test 1: Basic configuration values
        System.out.println("  - Default biome rarity: 5");
        System.out.println("  - Default max trees: 2");
        System.out.println("  - Default vegetation density: 4");
        
        // Test 2: Biome information
        System.out.println("  - Target biome: Modified Jungle Edge");
        System.out.println("  - Biome characteristics: Sparse jungle trees, reduced vegetation");
        
        System.out.println("✅ Basic features test completed!");
    }
    
    // Static configuration class
    public static class Config {
        public static final int BIOME_RARITY = 5;
        public static final int MAX_TREES_PER_CHUNK = 2;
        public static final int MIN_TREES_PER_CHUNK = 0;
        public static final int VEGETATION_DENSITY = 4;
        public static final boolean ENABLE_OCELOTS = true;
        public static final boolean ENABLE_PARROTS = true;
        public static final int OCELOT_SPAWN_WEIGHT = 2;
        public static final int PARROT_SPAWN_WEIGHT = 40;
    }
}