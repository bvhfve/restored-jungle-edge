# 🐛 Debug Guide - Restored Jungle Edge

This guide covers all debugging features available in the Restored Jungle Edge mod version 1.0.4+.

---

## 📖 Table of Contents

- [Overview](#overview)
- [Debug Classes](#debug-classes)
- [Getting Started](#getting-started)
- [Biome Debug Logging](#biome-debug-logging)
- [Configuration Debugging](#configuration-debugging)
- [Performance Profiling](#performance-profiling)
- [Log Analysis](#log-analysis)
- [Troubleshooting](#troubleshooting)
- [Advanced Usage](#advanced-usage)
- [FAQ](#faq)

---

## 🔍 Overview

The Restored Jungle Edge mod includes comprehensive debugging tools to help developers and advanced users:

- **BiomeDebugLogger**: Tracks biome generation process and statistics
- **ConfigDebugHelper**: Validates and debugs configuration issues
- **PerformanceProfiler**: Monitors performance and identifies bottlenecks

### Debug Features
- ✅ **Automatic Enablement**: Debug features are enabled by default in development
- 📊 **Real-time Statistics**: Live tracking of biome generation and performance
- 🔧 **Configuration Validation**: Comprehensive config value checking
- ⚠️ **Error Detection**: Detailed error reporting and analysis
- 📈 **Performance Monitoring**: Operation timing and optimization insights

---

## 🛠️ Debug Classes

### BiomeDebugLogger
**Location**: `com.bvhfve.restoredjungleedge.debug.BiomeDebugLogger`

**Purpose**: Specialized logging for biome generation debugging

**Key Features**:
- Biome generation timing and statistics
- Success/failure rate tracking
- Mob spawn configuration logging
- Biome characteristics validation
- Milestone achievement reporting

### ConfigDebugHelper
**Location**: `com.bvhfve.restoredjungleedge.debug.ConfigDebugHelper`

**Purpose**: Configuration validation and debugging utilities

**Key Features**:
- Full configuration validation
- Range checking for all values
- Default value comparison
- Configuration helper testing
- Detailed configuration reports

### PerformanceProfiler
**Location**: `com.bvhfve.restoredjungleedge.debug.PerformanceProfiler`

**Purpose**: Performance monitoring and optimization

**Key Features**:
- Operation timing and statistics
- Slow operation detection
- Performance summaries
- Thread-safe profiling
- Memory leak detection

---

## 🚀 Getting Started

### Automatic Debug Enablement

Debug features are automatically enabled when the mod loads:

```java
// Automatically enabled in RestoredJungleEdgeClean.java
BiomeDebugLogger.setVerboseLogging(true);
PerformanceProfiler.setEnabled(true);
ConfigDebugHelper.logFullConfiguration();
```

### Manual Control

You can control debug features programmatically:

```java
// Enable/disable verbose biome logging
BiomeDebugLogger.setVerboseLogging(true);

// Enable/disable performance profiling
PerformanceProfiler.setEnabled(true);
PerformanceProfiler.setDetailedLogging(false);

// Set slow operation threshold (in milliseconds)
PerformanceProfiler.setSlowOperationThreshold(10);
```

### Log Levels

The mod uses different log levels for different types of information:

- **INFO**: General information and statistics
- **DEBUG**: Detailed operation information
- **WARN**: Warnings and potential issues
- **ERROR**: Errors and failures

---

## 🌿 Biome Debug Logging

### What It Tracks

The BiomeDebugLogger tracks comprehensive biome generation information:

#### Generation Process
```log
🌿 Starting biome generation for: restoredjungleedge:modified_jungle_edge
  - Generation attempt #1
  - Thread: Server thread
```

#### Configuration Usage
```log
⚙️ Using config - ocelotSpawnWeight: 2
⚙️ Using config - parrotSpawnWeight: 40
```

#### Mob Spawn Configuration
```log
🐾 Configuring mob spawn - Type: OCELOT, Weight: 2, Group: 1-3
🐾 Configuring mob spawn - Type: PARROT, Weight: 40, Group: 1-2
```

#### Biome Characteristics
```log
🌡️ Biome characteristics for restoredjungleedge:modified_jungle_edge - Temperature: 0.95, Downfall: 0.80
```

#### Success/Failure Tracking
```log
✅ Successfully generated biome: restoredjungleedge:modified_jungle_edge
  - Generation time: 2.34ms

❌ Failed to generate biome: restoredjungleedge:modified_jungle_edge
  - Error: Registry not found
  - Duration before failure: 1.23ms
```

### Statistics and Milestones

#### Milestone Logging
```log
🎯 Milestone: 100 biomes generated successfully
📊 Processed 1000 chunks
```

#### Comprehensive Statistics
```log
📊 BIOME GENERATION STATISTICS:
  - Biomes Generated: 150
  - Chunks Processed: 1500
  - Errors Encountered: 2
  - Average Generation Time: 2.45ms
  - Total Generation Time: 367.50ms
  - Success Rate: 98.7%
```

### Controlling Biome Logging

```java
// Enable verbose logging for detailed information
BiomeDebugLogger.setVerboseLogging(true);

// Enable performance logging
BiomeDebugLogger.setPerformanceLogging(true);

// Enable error logging
BiomeDebugLogger.setErrorLogging(true);

// Get current statistics
String stats = BiomeDebugLogger.getStatisticsString();

// Reset all statistics
BiomeDebugLogger.resetStatistics();

// Log current statistics
BiomeDebugLogger.logStatistics();
```

---

## ⚙️ Configuration Debugging

### Full Configuration Report

The ConfigDebugHelper provides comprehensive configuration validation:

```log
🔧 CONFIGURATION DEBUG REPORT
==================================================

🌍 BIOME GENERATION CONFIG:
  - Biome Rarity: 5 (raw: 5) ✅ VALID
  - Temperature Variance: 10 ✅ VALID
  - Humidity Variance: 15 ✅ VALID
  - Enable In Existing Worlds: false
  - Replace Jungle Edge: false ✅ SAFE

🌳 BIOME FEATURES CONFIG:
  - Trees Per Chunk: 0-2 (raw: 0-2) ✅ VALID
  - Vegetation Density: 4 (raw: 4) ✅ VALID
  - Mega Tree Chance: 10.0% (raw: 10) ✅ VALID
  - Vine Growth Chance: 30.0% (raw: 30) ✅ VALID
  - Enable Cocoa Beans: true
  - Enable Melon Patches: true

🐾 MOB SPAWNING CONFIG:
  - Ocelots: ENABLED (Weight: 2, Raw: 2) ✅ VALID
  - Parrots: ENABLED (Weight: 40, Raw: 40) ✅ VALID
  - Hostile Mob Rate: 100.0% (raw: 100) ✅ VALID
  - Passive Mob Rate: 100.0% (raw: 100) ✅ VALID

✅ Configuration validation completed
```

### Validation Errors

When configuration values are out of range:

```log
🔧 CONFIGURATION DEBUG REPORT
==================================================

🌍 BIOME GENERATION CONFIG:
  - Biome Rarity: 150 (raw: 150) ❌ OUT OF RANGE [0-100]
  - Trees Per Chunk: 5-3 (raw: 5-3) ❌ MIN > MAX
  - Vegetation Density: 25 (raw: 25) ❌ OUT OF BOUNDS [0-20]
```

### Configuration Differences

Compare current configuration with defaults:

```log
🔍 CONFIGURATION DIFFERENCES FROM DEFAULTS:
  - biomeRarity: 5 → 10 (MORE COMMON)
  - maxTreesPerChunk: 2 → 4 (DENSER)
  - vegetationDensity: 4 → 8 (MORE VEGETATION)
```

### Configuration Helper Testing

Test all configuration helper methods:

```log
🧪 TESTING CONFIGURATION HELPERS:
  - getBiomeRarity(): 5
  - getMaxTreesPerChunk(): 2
  - getMinTreesPerChunk(): 0
  - getVegetationDensity(): 4
  - getMegaJungleTreeChance(): 0.1
  - getVineGrowthChance(): 0.3
  - getOcelotSpawnWeight(): 2
  - getParrotSpawnWeight(): 40
  - getHostileMobSpawnRate(): 1.0
  - getPassiveMobSpawnRate(): 1.0
✅ All configuration helpers working correctly
```

### Using Configuration Debug

```java
// Log full configuration with validation
ConfigDebugHelper.logFullConfiguration();

// Compare with defaults
ConfigDebugHelper.logConfigurationDifferences();

// Test all helper methods
ConfigDebugHelper.testConfigurationHelpers();
```

---

## ⚡ Performance Profiling

### Operation Timing

The PerformanceProfiler tracks timing for all critical operations:

```log
⏱️ Started timing: BiomeCreation [Thread: Server thread]
⏱️ Completed timing: BiomeCreation - 2.34ms
```

### Slow Operation Detection

Operations taking longer than the threshold are flagged:

```log
🐌 SLOW OPERATION: BiomeCreation took 15.67ms [Thread: Server thread]
```

### Performance Statistics

Comprehensive performance reports:

```log
📊 PERFORMANCE STATISTICS:
============================================================

🔍 BiomeCreation
  - Total: 367.50ms | Calls: 150 | Avg: 2.45ms
  - Min: 1.23ms | Max: 15.67ms

🔍 ConfigInitialization
  - Total: 45.20ms | Calls: 1 | Avg: 45.20ms
  - Min: 45.20ms | Max: 45.20ms

📈 SUMMARY:
  - Total Operations: 2
  - Total Overhead: 412.70ms
  - Active Timers: 0
```

### Using Performance Profiler

```java
// Manual timing
PerformanceProfiler.startTiming("MyOperation");
// ... do work ...
long duration = PerformanceProfiler.endTiming("MyOperation");

// Time a runnable
PerformanceProfiler.timeOperation("MyOperation", () -> {
    // ... do work ...
});

// Time a supplier
String result = PerformanceProfiler.timeOperation("MyOperation", () -> {
    return "result";
});

// Control profiler
PerformanceProfiler.setEnabled(true);
PerformanceProfiler.setDetailedLogging(true);
PerformanceProfiler.setSlowOperationThreshold(10); // 10ms

// Get statistics
PerformanceProfiler.logStatistics();
String summary = PerformanceProfiler.getOperationSummary("BiomeCreation");

// Reset data
PerformanceProfiler.reset();

// Check for leaked timers
PerformanceProfiler.checkForLeakedTimers();
```

---

## 📊 Log Analysis

### Reading Debug Logs

Debug logs use emojis and structured formatting for easy reading:

#### Symbols and Their Meanings
- 🌿 **Biome Generation**: Biome-related operations
- ⚙️ **Configuration**: Configuration usage and validation
- 🐾 **Mob Spawning**: Mob spawn configuration
- 🌡️ **Biome Characteristics**: Temperature, humidity, etc.
- ⏱️ **Performance**: Timing and performance data
- 📊 **Statistics**: Reports and summaries
- ✅ **Success**: Successful operations
- ❌ **Error**: Failed operations
- ⚠️ **Warning**: Potential issues
- 🎯 **Milestone**: Achievement markers
- 🔍 **Analysis**: Detailed analysis
- 🧪 **Testing**: Test operations

### Log Filtering

To filter logs by category, search for these patterns:

```bash
# Biome generation logs
grep "🌿\|🐾\|🌡️" minecraft.log

# Performance logs
grep "⏱️\|📊\|🐌" minecraft.log

# Configuration logs
grep "⚙️\|🔧\|🧪" minecraft.log

# Errors and warnings
grep "❌\|⚠️" minecraft.log

# Statistics and milestones
grep "📊\|🎯" minecraft.log
```

### Common Log Patterns

#### Successful Biome Generation
```log
🌿 Starting biome generation for: restoredjungleedge:modified_jungle_edge
⚙️ Using config - ocelotSpawnWeight: 2
🐾 Configuring mob spawn - Type: OCELOT, Weight: 2, Group: 1-3
🌡️ Biome characteristics for restoredjungleedge:modified_jungle_edge - Temperature: 0.95, Downfall: 0.80
✅ Successfully generated biome: restoredjungleedge:modified_jungle_edge
```

#### Configuration Issues
```log
🔧 CONFIGURATION DEBUG REPORT
❌ OUT OF RANGE [0-100]
⚠️ Configuration initialization failed, using defaults
```

#### Performance Issues
```log
🐌 SLOW OPERATION: BiomeCreation took 15.67ms
⚠️ 3 leaked timers detected
```

---

## 🔧 Troubleshooting

### Common Issues and Solutions

#### Issue: No Debug Logs Appearing
**Symptoms**: No debug logs in console/log files
**Solutions**:
```java
// Check if debug logging is enabled
BiomeDebugLogger.setVerboseLogging(true);
PerformanceProfiler.setEnabled(true);

// Check log level configuration
// Ensure DEBUG level is enabled in your logging configuration
```

#### Issue: Configuration Validation Errors
**Symptoms**: Configuration values marked as invalid
**Solutions**:
1. Check configuration file syntax
2. Verify values are within valid ranges
3. Reset configuration to defaults
4. Check for typos in configuration keys

#### Issue: Performance Warnings
**Symptoms**: Slow operation warnings
**Solutions**:
1. Check system performance
2. Reduce configuration complexity
3. Monitor memory usage
4. Check for mod conflicts

#### Issue: Biome Generation Failures
**Symptoms**: Biome generation error logs
**Solutions**:
1. Check registry availability
2. Verify mod dependencies
3. Check for conflicting mods
4. Review configuration values

### Debug Log Levels

Adjust logging verbosity based on your needs:

```java
// Minimal logging (errors only)
BiomeDebugLogger.setVerboseLogging(false);
BiomeDebugLogger.setPerformanceLogging(false);
PerformanceProfiler.setDetailedLogging(false);

// Standard logging (recommended)
BiomeDebugLogger.setVerboseLogging(true);
BiomeDebugLogger.setPerformanceLogging(true);
PerformanceProfiler.setDetailedLogging(false);

// Maximum logging (development only)
BiomeDebugLogger.setVerboseLogging(true);
BiomeDebugLogger.setPerformanceLogging(true);
PerformanceProfiler.setDetailedLogging(true);
```

---

## 🚀 Advanced Usage

### Custom Debug Integration

You can integrate with the debug system in your own code:

```java
// Add custom biome generation logging
BiomeDebugLogger.logFeatureAddition("CUSTOM_TREE", "MyCustomTree");
BiomeDebugLogger.logChunkProcessing(chunkX, chunkZ, "CUSTOM_PHASE");

// Add custom performance monitoring
PerformanceProfiler.timeOperation("MyCustomOperation", () -> {
    // Your custom code here
});

// Add custom configuration validation
ConfigDebugHelper.logConfigurationUsage("myCustomConfig", value);
```

### Automated Monitoring

Set up automated monitoring for production environments:

```java
// Monitor performance and log warnings
PerformanceProfiler.setSlowOperationThreshold(5); // 5ms threshold

// Periodic statistics logging
// (implement in a scheduled task)
BiomeDebugLogger.logStatistics();
PerformanceProfiler.logStatistics();

// Check for memory leaks
PerformanceProfiler.checkForLeakedTimers();
```

### Debug Data Export

Export debug data for analysis:

```java
// Get statistics as strings for export
String biomeStats = BiomeDebugLogger.getStatisticsString();
String perfSummary = PerformanceProfiler.getOperationSummary("BiomeCreation");

// Log to files or send to monitoring systems
// (implement based on your needs)
```

---

## ❓ FAQ

### Q: How do I disable debug logging?
**A**: Set all debug features to false:
```java
BiomeDebugLogger.setVerboseLogging(false);
PerformanceProfiler.setEnabled(false);
```

### Q: Are debug features safe for production?
**A**: Yes, but consider disabling verbose logging for performance:
- Keep error logging enabled
- Disable verbose and detailed logging
- Monitor performance impact

### Q: How much performance overhead do debug features add?
**A**: Minimal when properly configured:
- Verbose logging: ~1-2% overhead
- Performance profiling: ~0.5% overhead
- Configuration validation: One-time cost at startup

### Q: Can I customize the debug output format?
**A**: The format is fixed, but you can:
- Filter logs using grep/search
- Parse logs programmatically
- Extend the debug classes for custom formats

### Q: How do I report debug information for bug reports?
**A**: Include these logs in bug reports:
1. Full configuration debug report
2. Recent biome generation statistics
3. Performance statistics
4. Any error messages with stack traces

### Q: Can debug features help with mod compatibility issues?
**A**: Yes, debug logs can help identify:
- Configuration conflicts
- Performance bottlenecks
- Registry issues
- Timing problems

---

## 📞 Support

If you need help with debugging:

1. **Check this guide** for common solutions
2. **Review log files** for error patterns
3. **Enable verbose logging** to get more details
4. **Create GitHub issues** with debug logs attached
5. **Join our Discord** for real-time support

### Useful Commands for Bug Reports

```bash
# Capture relevant logs
grep -A5 -B5 "❌\|⚠️" minecraft.log > errors.log
grep "📊.*STATISTICS" minecraft.log > statistics.log
grep "🔧.*CONFIG" minecraft.log > config.log
```

---

<div align="center">

**🐛 Happy Debugging! 🐛**

*This guide covers Restored Jungle Edge v1.0.4+ debug features*

[⬆️ Back to Top](#-debug-guide---restored-jungle-edge)

</div>