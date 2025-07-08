package com.bvhfve.restoredjungleedge.client;

import com.bvhfve.restoredjungleedge.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;

// Note: ModMenu integration is optional - these imports will only work if ModMenu is present
// import com.terraformersmc.modmenu.api.ConfigScreenFactory;
// import com.terraformersmc.modmenu.api.ModMenuApi;

// ModMenu integration - only active when ModMenu is present
public class ModMenuIntegration {
    
    // This will be loaded via reflection when ModMenu is available
    public static Object getModConfigScreenFactory() {
        try {
            // Use reflection to avoid hard dependency on ModMenu
            Class<?> configScreenFactoryClass = Class.forName("com.terraformersmc.modmenu.api.ConfigScreenFactory");
            // Return null for now to avoid compilation issues
            return null;
        } catch (ClassNotFoundException e) {
            return null; // ModMenu not present
        }
    }
}