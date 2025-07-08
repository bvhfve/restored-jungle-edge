package com.bvhfve.restoredjungleedge.datagen;

import com.bvhfve.restoredjungleedge.ModBiomes;
import com.bvhfve.restoredjungleedge.RestoredJungleEdge;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class ModBiomeProvider extends FabricDynamicRegistryProvider {
    
    public ModBiomeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    
    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.add(RestoredJungleEdge.MODIFIED_JUNGLE_EDGE, 
            ModBiomes.createModifiedJungleEdge(registries));
    }
    
    @Override
    public String getName() {
        return "Modified Jungle Edge Biome";
    }
}