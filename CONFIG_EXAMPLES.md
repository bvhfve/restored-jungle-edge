# Configuration Examples

This document provides practical examples of different configuration setups for the Restored Jungle Edge mod.

## Preset Configurations

### 1. Vanilla-Like (Default)
Closely matches the original Modified Jungle Edge biome behavior.

```json
{
  "biomeGeneration": {
    "biomeRarity": 5,
    "temperatureVariance": 10,
    "humidityVariance": 15,
    "enableInExistingWorlds": false,
    "replaceJungleEdge": false
  },
  "biomeFeatures": {
    "maxTreesPerChunk": 2,
    "minTreesPerChunk": 0,
    "megaJungleTreeChance": 10,
    "vineGrowthChance": 30,
    "vegetationDensity": 4,
    "enableCocoaBeans": true,
    "enableMelonPatches": true
  },
  "mobSpawning": {
    "enableOcelots": true,
    "enableParrots": true,
    "ocelotSpawnWeight": 2,
    "parrotSpawnWeight": 40,
    "hostileMobSpawnRate": 100,
    "passiveMobSpawnRate": 100
  }
}
```

### 2. Ultra Rare
Makes the biome extremely rare for those who want a special discovery.

```json
{
  "biomeGeneration": {
    "biomeRarity": 1,
    "temperatureVariance": 5,
    "humidityVariance": 5,
    "enableInExistingWorlds": false,
    "replaceJungleEdge": false
  },
  "biomeFeatures": {
    "maxTreesPerChunk": 1,
    "minTreesPerChunk": 0,
    "megaJungleTreeChance": 5,
    "vineGrowthChance": 20,
    "vegetationDensity": 2,
    "enableCocoaBeans": true,
    "enableMelonPatches": false
  },
  "mobSpawning": {
    "enableOcelots": true,
    "enableParrots": true,
    "ocelotSpawnWeight": 1,
    "parrotSpawnWeight": 20,
    "hostileMobSpawnRate": 80,
    "passiveMobSpawnRate": 80
  }
}
```

### 3. Dense Jungle
More forest-like with increased vegetation and wildlife.

```json
{
  "biomeGeneration": {
    "biomeRarity": 15,
    "temperatureVariance": 20,
    "humidityVariance": 25,
    "enableInExistingWorlds": false,
    "replaceJungleEdge": false
  },
  "biomeFeatures": {
    "maxTreesPerChunk": 5,
    "minTreesPerChunk": 2,
    "megaJungleTreeChance": 25,
    "vineGrowthChance": 60,
    "vegetationDensity": 8,
    "enableCocoaBeans": true,
    "enableMelonPatches": true
  },
  "mobSpawning": {
    "enableOcelots": true,
    "enableParrots": true,
    "ocelotSpawnWeight": 5,
    "parrotSpawnWeight": 60,
    "hostileMobSpawnRate": 120,
    "passiveMobSpawnRate": 130
  }
}
```

### 4. Sparse Savanna-Like
More open with minimal trees, resembling a transition biome.

```json
{
  "biomeGeneration": {
    "biomeRarity": 8,
    "temperatureVariance": 15,
    "humidityVariance": 10,
    "enableInExistingWorlds": false,
    "replaceJungleEdge": false
  },
  "biomeFeatures": {
    "maxTreesPerChunk": 1,
    "minTreesPerChunk": 0,
    "megaJungleTreeChance": 2,
    "vineGrowthChance": 10,
    "vegetationDensity": 2,
    "enableCocoaBeans": false,
    "enableMelonPatches": false
  },
  "mobSpawning": {
    "enableOcelots": false,
    "enableParrots": true,
    "ocelotSpawnWeight": 0,
    "parrotSpawnWeight": 20,
    "hostileMobSpawnRate": 90,
    "passiveMobSpawnRate": 110
  }
}
```

### 5. Wildlife Sanctuary
Focused on animal spawning with reduced hostile mobs.

```json
{
  "biomeGeneration": {
    "biomeRarity": 10,
    "temperatureVariance": 15,
    "humidityVariance": 20,
    "enableInExistingWorlds": false,
    "replaceJungleEdge": false
  },
  "biomeFeatures": {
    "maxTreesPerChunk": 3,
    "minTreesPerChunk": 1,
    "megaJungleTreeChance": 15,
    "vineGrowthChance": 40,
    "vegetationDensity": 6,
    "enableCocoaBeans": true,
    "enableMelonPatches": true
  },
  "mobSpawning": {
    "enableOcelots": true,
    "enableParrots": true,
    "ocelotSpawnWeight": 8,
    "parrotSpawnWeight": 80,
    "hostileMobSpawnRate": 60,
    "passiveMobSpawnRate": 150
  }
}
```

## Scenario-Based Configurations

### For Modpack Integration
When using with other biome mods, you might want to reduce conflicts:

```json
{
  "biomeGeneration": {
    "biomeRarity": 3,
    "temperatureVariance": 8,
    "humidityVariance": 12,
    "enableInExistingWorlds": false,
    "replaceJungleEdge": false
  }
}
```

### For Existing Worlds
If you want to add the biome to an existing world (use with caution):

```json
{
  "biomeGeneration": {
    "biomeRarity": 7,
    "temperatureVariance": 15,
    "humidityVariance": 20,
    "enableInExistingWorlds": true,
    "replaceJungleEdge": false
  }
}
```

### For Jungle Edge Replacement
To completely replace vanilla jungle edge biomes:

```json
{
  "biomeGeneration": {
    "biomeRarity": 20,
    "temperatureVariance": 25,
    "humidityVariance": 30,
    "enableInExistingWorlds": false,
    "replaceJungleEdge": true
  }
}
```

## Performance Optimization

### Low-End Systems
Reduced features for better performance:

```json
{
  "biomeFeatures": {
    "maxTreesPerChunk": 1,
    "minTreesPerChunk": 0,
    "megaJungleTreeChance": 5,
    "vineGrowthChance": 20,
    "vegetationDensity": 2,
    "enableCocoaBeans": false,
    "enableMelonPatches": false
  },
  "mobSpawning": {
    "hostileMobSpawnRate": 80,
    "passiveMobSpawnRate": 80
  }
}
```

### High-End Systems
Maximum features for rich experience:

```json
{
  "biomeFeatures": {
    "maxTreesPerChunk": 6,
    "minTreesPerChunk": 2,
    "megaJungleTreeChance": 30,
    "vineGrowthChance": 70,
    "vegetationDensity": 10,
    "enableCocoaBeans": true,
    "enableMelonPatches": true
  },
  "mobSpawning": {
    "hostileMobSpawnRate": 120,
    "passiveMobSpawnRate": 140
  }
}
```

## Testing Configurations

### Debug Mode
For testing and world generation analysis:

```json
{
  "biomeGeneration": {
    "biomeRarity": 50,
    "temperatureVariance": 50,
    "humidityVariance": 50,
    "enableInExistingWorlds": true,
    "replaceJungleEdge": false
  }
}
```

### Minimal Features
For testing biome generation without features:

```json
{
  "biomeFeatures": {
    "maxTreesPerChunk": 0,
    "minTreesPerChunk": 0,
    "megaJungleTreeChance": 0,
    "vineGrowthChance": 0,
    "vegetationDensity": 0,
    "enableCocoaBeans": false,
    "enableMelonPatches": false
  },
  "mobSpawning": {
    "enableOcelots": false,
    "enableParrots": false,
    "hostileMobSpawnRate": 50,
    "passiveMobSpawnRate": 50
  }
}
```

## Tips for Custom Configurations

1. **Start with defaults** and modify one category at a time
2. **Test in creative mode** with `/locate biome` command
3. **Backup your world** before making major changes
4. **Use lower rarity values** for special/rare biomes
5. **Balance tree density** with vegetation for realistic appearance
6. **Consider mob spawning balance** for gameplay experience

## Configuration Validation

The mod automatically validates configuration values and will:
- Clamp values to their allowed ranges
- Log warnings for invalid settings
- Fall back to defaults for corrupted values

Always check your game logs if configurations aren't working as expected.