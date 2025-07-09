# 🌿 Restored Jungle Edge

[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.4-brightgreen.svg)](https://minecraft.net/)
[![Fabric](https://img.shields.io/badge/Fabric-0.16.5+-blue.svg)](https://fabricmc.net/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Version](https://img.shields.io/badge/Version-1.0.5-orange.svg)](https://github.com/bvhfve/restored-jungle-edge/releases)

**Restore the beloved Modified Jungle Edge biome that was removed from Minecraft!**

The Modified Jungle Edge was a rare and beautiful biome variant that provided a unique transition between jungle and other biomes. This mod brings it back with full customization options and modern Minecraft compatibility.

---

## 📖 Overview

The **Restored Jungle Edge** mod recreates the iconic **Modified Jungle Edge** biome that was removed from Minecraft in recent versions. This biome featured:

- 🌳 **Sparse jungle trees** - Fewer trees than regular jungle biomes
- 🌿 **Reduced vegetation** - More open areas with scattered grass and ferns
- 🐾 **Unique wildlife** - Ocelots and parrots in a more open environment
- 🏞️ **Transition zones** - Perfect buffer between jungle and other biomes
- ✨ **Rare generation** - Maintains the original rarity and special feel

---

## ✨ Features

### 🎯 Core Features
- **Authentic Recreation**: Faithful restoration of the original Modified Jungle Edge biome
- **Configurable Generation**: Adjust rarity, tree density, and vegetation to your liking
- **Modern Compatibility**: Built for Minecraft 1.21.4 with Fabric
- **Performance Optimized**: Lightweight implementation with minimal impact
- **Modpack Friendly**: Easy integration with other biome and world generation mods

### 🔧 Customization Options
- **Biome Rarity**: Control how often the biome generates (0-100)
- **Tree Density**: Adjust minimum and maximum trees per chunk (0-10)
- **Vegetation**: Configure grass, fern, and ground cover density (0-20)
- **Wildlife**: Enable/disable and adjust spawn rates for ocelots and parrots
- **World Integration**: Choose whether to add to existing worlds

### 🎮 User Experience
- **ModMenu Integration**: Easy in-game configuration (when ModMenu is installed)
- **Smart Defaults**: Works perfectly out of the box with sensible settings
- **Extensive Documentation**: Complete configuration guides and examples
- **Multiple Presets**: Ready-to-use configurations for different play styles

---

## 🚀 Installation

### Requirements
- **Minecraft**: 1.21.4
- **Fabric Loader**: 0.16.5 or higher
- **Fabric API**: 0.119.2+1.21.4 or higher

### Optional Dependencies
- **ModMenu**: 13.0.0+ (for in-game configuration GUI)

### Installation Steps

1. **Install Fabric Loader**
   - Download from [FabricMC](https://fabricmc.net/use/installer/)
   - Run the installer and select Minecraft 1.21.4

2. **Download Required Mods**
   - [Fabric API](https://modrinth.com/mod/fabric-api)
   - [ModMenu](https://modrinth.com/mod/modmenu) (optional but recommended)

3. **Install Restored Jungle Edge**
   - Download the latest release from [Releases](https://github.com/bvhfve/restored-jungle-edge/releases)
   - Place the `.jar` file in your `.minecraft/mods` folder

4. **Launch Minecraft**
   - Select the Fabric profile
   - Create a new world or explore new chunks in existing worlds

---

## ⚙️ Configuration

### Quick Start

The mod works perfectly with default settings, but you can customize it to your preferences:

**Config File Location**: `.minecraft/config/restoredjungleedge.json`

### In-Game Configuration (Recommended)

1. Install [ModMenu](https://modrinth.com/mod/modmenu)
2. Main Menu → **Mods** → **Restored Jungle Edge** → **Config** (⚙️)
3. Adjust settings with intuitive sliders and toggles
4. Click **Done** to save changes

### Key Settings

| Setting | Description | Default | Range |
|---------|-------------|---------|-------|
| `biomeRarity` | How often the biome spawns | 5 | 0-100 |
| `maxTreesPerChunk` | Maximum trees per chunk | 2 | 0-10 |
| `minTreesPerChunk` | Minimum trees per chunk | 0 | 0-10 |
| `vegetationDensity` | Ground vegetation amount | 4 | 0-20 |
| `enableOcelots` | Enable ocelot spawning | true | true/false |
| `enableParrots` | Enable parrot spawning | true | true/false |

### Example Configurations

#### More Common Generation
```json
{
  "biomeRarity": 15,
  "maxTreesPerChunk": 3,
  "vegetationDensity": 6
}
```

#### Ultra Rare (Original Feel)
```json
{
  "biomeRarity": 2,
  "maxTreesPerChunk": 1,
  "vegetationDensity": 2
}
```

#### Dense Jungle Edge
```json
{
  "biomeRarity": 8,
  "maxTreesPerChunk": 4,
  "minTreesPerChunk": 1,
  "vegetationDensity": 8
}
```

---

## 🗺️ Finding the Biome

### Locating Modified Jungle Edge

**Command**: `/locate biome restoredjungleedge:modified_jungle_edge`

**Natural Generation**: 
- Spawns near jungle biomes as transition zones
- Look for areas with sparse jungle trees
- Check the F3 debug screen for biome name
- Most commonly found at jungle borders

**Biome Characteristics**:
- **Temperature**: 0.95 (warm)
- **Humidity**: 0.8 (humid)
- **Grass Color**: Vibrant jungle green
- **Sky Color**: Tropical blue
- **Tree Density**: Noticeably sparser than regular jungle

---

## 🔧 For Modpack Creators

### Integration Tips

- **Biome Weight**: Adjust `biomeRarity` based on other biome mods
- **Compatibility**: Works with most world generation mods
- **Performance**: Minimal impact on world generation speed
- **Customization**: Extensive configuration options for pack balance

### Recommended Settings for Modpacks

#### Large Modpacks (Many Biomes)
```json
{
  "biomeRarity": 3,
  "maxTreesPerChunk": 2,
  "vegetationDensity": 4
}
```

#### Vanilla+ Packs
```json
{
  "biomeRarity": 5,
  "maxTreesPerChunk": 2,
  "vegetationDensity": 4
}
```

#### Adventure/Exploration Packs
```json
{
  "biomeRarity": 8,
  "maxTreesPerChunk": 3,
  "vegetationDensity": 6
}
```

---

## 🐛 Troubleshooting

### Common Issues

**Q: The biome isn't spawning in my world**
- A: Increase `biomeRarity` to 10-15 and explore new chunks
- A: Use `/locate biome restoredjungleedge:modified_jungle_edge` to find existing ones

**Q: Too many/few trees in the biome**
- A: Adjust `maxTreesPerChunk` and `minTreesPerChunk` values
- A: Changes only affect newly generated chunks

**Q: Configuration changes aren't working**
- A: Restart Minecraft after editing the config file
- A: Ensure the JSON syntax is valid
- A: Delete the config file to reset to defaults

**Q: Mod not loading**
- A: Check that you have the correct Minecraft version (1.21.4)
- A: Ensure Fabric Loader and Fabric API are installed
- A: Check the logs for error messages

### Performance Tips

- Lower `biomeRarity` if experiencing world generation lag
- Reduce `vegetationDensity` for better performance
- The mod is optimized and should have minimal impact

---

## 🤝 Contributing

We welcome contributions to improve the Restored Jungle Edge mod!

### How to Contribute

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Development Setup

```bash
git clone https://github.com/bvhfve/restored-jungle-edge.git
cd restored-jungle-edge
./gradlew build
```

### Areas for Contribution

- 🌍 **Translations**: Help translate the mod to other languages
- 🎨 **Textures**: Improve biome-specific textures or features
- 🔧 **Features**: Add new customization options
- 📚 **Documentation**: Improve guides and examples
- 🐛 **Bug Fixes**: Report and fix issues

---

## 📜 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

### What this means:
- ✅ **Free to use** in personal and commercial projects
- ✅ **Free to modify** and distribute
- ✅ **Free to include** in modpacks
- ✅ **No attribution required** (but appreciated!)

---

## 🙏 Acknowledgments

- **Mojang Studios** - For creating Minecraft and the original Modified Jungle Edge biome
- **FabricMC Team** - For the excellent modding framework
- **Minecraft Community** - For keeping the memory of removed features alive
- **Contributors** - Everyone who helps improve this mod

---

## 📞 Support & Links

### Get Help
- 🐛 **Bug Reports**: [GitHub Issues](https://github.com/bvhfve/restored-jungle-edge/issues)
- 💬 **Discussions**: [GitHub Discussions](https://github.com/bvhfve/restored-jungle-edge/discussions)
- 📖 **Documentation**: [Configuration Guide](CONFIGURATION.md)

### Download Links
- 📦 **Modrinth**: [Restored Jungle Edge](https://modrinth.com/mod/restored-jungle-edge)
- 📦 **CurseForge**: [Restored Jungle Edge](https://curseforge.com/minecraft/mc-mods/restored-jungle-edge)
- 📦 **GitHub Releases**: [Latest Release](https://github.com/bvhfve/restored-jungle-edge/releases/latest)

### Social
- 🐦 **Twitter**: [@bvhfve](https://twitter.com/bvhfve)
- 💬 **Discord**: Join our [Discord Server](https://discord.gg/restored-jungle-edge)

---

## 📊 Statistics

- **Downloads**: ![Downloads](https://img.shields.io/github/downloads/bvhfve/restored-jungle-edge/total)
- **Stars**: ![Stars](https://img.shields.io/github/stars/bvhfve/restored-jungle-edge)
- **Issues**: ![Issues](https://img.shields.io/github/issues/bvhfve/restored-jungle-edge)
- **Last Updated**: ![Last Commit](https://img.shields.io/github/last-commit/bvhfve/restored-jungle-edge)

---

<div align="center">

**🌿 Bring back the beauty of the Modified Jungle Edge! 🌿**

*Made with ❤️ for the Minecraft community*

[⬆️ Back to Top](#-restored-jungle-edge)

</div>