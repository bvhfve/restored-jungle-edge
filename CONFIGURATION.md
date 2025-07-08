# Restored Jungle Edge - Configuration Guide

This document provides comprehensive information about configuring the Restored Jungle Edge mod.

## Table of Contents
- [Configuration File Location](#configuration-file-location)
- [Configuration Categories](#configuration-categories)
- [Biome Generation Settings](#biome-generation-settings)
- [Biome Features Settings](#biome-features-settings)
- [Mob Spawning Settings](#mob-spawning-settings)
- [Advanced Configuration](#advanced-configuration)
- [Troubleshooting](#troubleshooting)

## Configuration File Location

The configuration file is automatically generated when you first run the mod:

**File Path:** `.minecraft/config/restoredjungleedge.json`

**Format:** JSON (automatically managed by Cloth Config)

## Configuration Categories

The mod configuration is divided into three main categories:

### 1. Biome Generation
Controls how and where the Modified Jungle Edge biome spawns in your world.

### 2. Biome Features  
Manages the vegetation, trees, and natural features within the biome.

### 3. Mob Spawning
Configures which creatures spawn in the biome and their spawn rates.

---

## Biome Generation Settings

### `biomeRarity` (Default: 5)
- **Range:** 0-100
- **Description:** Controls how rare the Modified Jungle Edge biome is
- **Lower values:** More rare (harder to find)
- **Higher values:** More common
- **Note:** Setting to 0 disables biome generation entirely

### `temperatureVariance` (Default: 10)
- **Range:** 0-200
- **Description:** Adjusts the temperature range where the biome can spawn
- **Lower values:** Stricter temperature requirements
- **Higher values:** More flexible temperature conditions

### `humidityVariance` (Default: 15)
- **Range:** 0-200
- **Description:** Adjusts the humidity range where the biome can spawn
- **Lower values:** Stricter humidity requirements
- **Higher values:** More flexible humidity conditions

### `enableInExistingWorlds` (Default: false)
- **Type:** Boolean
- **Description:** Whether to generate the biome in already explored chunks
- **Warning:** May cause chunk borders and terrain inconsistencies

### `replaceJungleEdge` (Default: false)
- **Type:** Boolean
- **Description:** Replace existing Jungle Edge biomes with Modified Jungle Edge
- **Warning:** This is irreversible and affects world generation permanently

---

## Biome Features Settings

### Tree Configuration

#### `maxTreesPerChunk` (Default: 2)
- **Range:** 0-10
- **Description:** Maximum number of trees that can spawn per chunk
- **Note:** The actual number is randomly chosen between min and max

#### `minTreesPerChunk` (Default: 0)
- **Range:** 0-10
- **Description:** Minimum number of trees that spawn per chunk
- **Note:** Must be less than or equal to maxTreesPerChunk

#### `megaJungleTreeChance` (Default: 10)
- **Range:** 0-100
- **Description:** Percentage chance for large 2x2 jungle trees to spawn
- **Higher values:** More mega trees (denser canopy)
- **Lower values:** Mostly single-block trunk trees

#### `vineGrowthChance` (Default: 30)
- **Range:** 0-100
- **Description:** Percentage chance for vines to grow on trees
- **Higher values:** More vines (more jungle-like appearance)
- **Lower values:** Cleaner trees with fewer vines

### Vegetation Configuration

#### `vegetationDensity` (Default: 4)
- **Range:** 0-20
- **Description:** Number of vegetation patches per chunk (grass, ferns, etc.)
- **Higher values:** Denser ground cover
- **Lower values:** More sparse, open areas

#### `enableCocoaBeans` (Default: true)
- **Type:** Boolean
- **Description:** Whether cocoa beans can grow on jungle trees in this biome

#### `enableMelonPatches` (Default: true)
- **Type:** Boolean
- **Description:** Whether melon patches can spawn in this biome

---

## Mob Spawning Settings

### Jungle-Specific Mobs

#### `enableOcelots` (Default: true)
- **Type:** Boolean
- **Description:** Whether ocelots can spawn in this biome

#### `enableParrots` (Default: true)
- **Type:** Boolean
- **Description:** Whether parrots can spawn in this biome

#### `ocelotSpawnWeight` (Default: 2)
- **Range:** 0-100
- **Description:** Relative spawn weight for ocelots
- **Higher values:** More frequent ocelot spawns

#### `parrotSpawnWeight` (Default: 40)
- **Range:** 0-100
- **Description:** Relative spawn weight for parrots
- **Higher values:** More frequent parrot spawns

### General Mob Rates

#### `hostileMobSpawnRate` (Default: 100)
- **Range:** 50-150
- **Description:** Multiplier for hostile mob spawn rates
- **100 = Normal rates**
- **50 = Half spawn rate**
- **150 = 1.5x spawn rate**

#### `passiveMobSpawnRate` (Default: 100)
- **Range:** 50-150
- **Description:** Multiplier for passive mob spawn rates
- **100 = Normal rates**
- **50 = Half spawn rate**
- **150 = 1.5x spawn rate**

---

## Advanced Configuration

### Manual Configuration

You can manually edit the configuration file at `.minecraft/config/restoredjungleedge.json`:

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

### In-Game Configuration

If you have **ModMenu** installed, you can access the configuration screen:

1. Open the main menu
2. Click "Mods"
3. Find "Restored Jungle Edge"
4. Click the config button (gear icon)

---

## Troubleshooting

### Common Issues

#### "Biome not generating"
- Check that `biomeRarity` is not set to 0
- Ensure you're exploring new chunks (unless `enableInExistingWorlds` is true)
- Try increasing `temperatureVariance` and `humidityVariance`

#### "Too many/few trees"
- Adjust `maxTreesPerChunk` and `minTreesPerChunk`
- Modify `megaJungleTreeChance` for tree size variety

#### "No jungle animals spawning"
- Verify `enableOcelots` and `enableParrots` are true
- Check that spawn weights are not set to 0
- Ensure the biome is actually generating (use F3 to check biome name)

#### "Configuration not saving"
- Make sure Minecraft has write permissions to the config folder
- Check that the JSON syntax is valid if manually edited
- Restart Minecraft after making changes

### Reset Configuration

To reset to default settings:
1. Close Minecraft
2. Delete `.minecraft/config/restoredjungleedge.json`
3. Restart Minecraft (file will be regenerated with defaults)

### Performance Considerations

- Higher `vegetationDensity` values may impact world generation performance
- Setting `biomeRarity` too high may affect world generation balance
- Very high spawn rates may impact game performance in the biome

---

## Version Compatibility

This configuration system is compatible with:
- **Minecraft:** 1.21.4
- **Fabric Loader:** 0.16.5+
- **Fabric API:** 0.119.2+1.21.4
- **Cloth Config:** 17.0.144

For questions or issues, please refer to the mod's GitHub repository or community forums.