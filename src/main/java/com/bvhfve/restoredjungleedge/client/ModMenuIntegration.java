package com.bvhfve.restoredjungleedge.client;

import com.bvhfve.restoredjungleedge.config.ModConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

// ModMenu integration - provides configuration screen in ModMenu
public class ModMenuIntegration implements ModMenuApi {
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // AutoConfig removed - return null for now
        // TODO: Implement custom config screen or use alternative
        return screen -> null;
    }
}