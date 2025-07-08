# Quick Start Configuration Guide

## 🚀 Getting Started

The Restored Jungle Edge mod works out of the box with sensible defaults, but you can customize it to your liking!

## 📍 Configuration Location

**Config File:** `.minecraft/config/restoredjungleedge.json`

This file is automatically created when you first run the mod.

## 🎮 In-Game Configuration (Recommended)

### With ModMenu (Easiest)
1. Install [ModMenu](https://modrinth.com/mod/modmenu)
2. Main Menu → **Mods** → **Restored Jungle Edge** → **Config** (⚙️)
3. Adjust settings with sliders and toggles
4. Click **Done** to save

### Manual Editing
1. Close Minecraft
2. Edit `.minecraft/config/restoredjungleedge.json`
3. Save and restart Minecraft

## ⚡ Quick Configurations

### Make it More Common
```json
"biomeRarity": 15
```

### Make it Rarer
```json
"biomeRarity": 2
```

### More Trees
```json
"maxTreesPerChunk": 4,
"minTreesPerChunk": 1
```

### Less Trees (More Open)
```json
"maxTreesPerChunk": 1,
"minTreesPerChunk": 0
```

### More Wildlife
```json
"ocelotSpawnWeight": 5,
"parrotSpawnWeight": 60,
"passiveMobSpawnRate": 130
```

## 🔧 Most Important Settings

| Setting | What it Does | Default | Range |
|---------|--------------|---------|-------|
| `biomeRarity` | How often the biome spawns | 5 | 0-100 |
| `maxTreesPerChunk` | Maximum trees per chunk | 2 | 0-10 |
| `vegetationDensity` | Ground vegetation amount | 4 | 0-20 |
| `enableInExistingWorlds` | Add to old worlds | false | true/false |

## ⚠️ Important Notes

- **Backup your world** before making major changes
- Changes only affect **new chunks** (unless `enableInExistingWorlds` is true)
- Setting `biomeRarity` to 0 **disables** the biome completely
- `replaceJungleEdge` is **permanent** - use with caution!

## 🔍 Finding the Biome

Use these commands to locate the biome:
```
/locate biome restoredjungleedge:modified_jungle_edge
```

Or check with F3 debug screen when exploring.

## 🆘 Troubleshooting

**Biome not spawning?**
- Increase `biomeRarity` (try 10-15)
- Explore new areas
- Check config file exists

**Too many/few trees?**
- Adjust `maxTreesPerChunk` and `minTreesPerChunk`

**Config not working?**
- Restart Minecraft after changes
- Check JSON syntax is valid
- Delete config file to reset to defaults

## 📚 Full Documentation

For complete configuration options, see:
- [CONFIGURATION.md](CONFIGURATION.md) - Complete reference
- [CONFIG_EXAMPLES.md](CONFIG_EXAMPLES.md) - Example setups

## 🎯 Recommended Starting Points

### First Time Users
Keep defaults, just try:
```json
"biomeRarity": 10
```

### Experienced Players
Try the "Dense Jungle" preset from CONFIG_EXAMPLES.md

### Modpack Creators
Start with "Ultra Rare" preset and adjust based on other biome mods