package com.bvhfve.restoredjungleedge.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;

/**
 * Performance profiler for tracking mod performance metrics
 */
public class PerformanceProfiler {
    private static final Logger LOGGER = LoggerFactory.getLogger("RestoredJungleEdge-Performance");
    
    // Performance tracking maps
    private static final Map<String, AtomicLong> totalTimes = new ConcurrentHashMap<>();
    private static final Map<String, AtomicInteger> callCounts = new ConcurrentHashMap<>();
    private static final Map<String, AtomicLong> maxTimes = new ConcurrentHashMap<>();
    private static final Map<String, AtomicLong> minTimes = new ConcurrentHashMap<>();
    
    // Active timers
    private static final Map<String, Long> activeTimers = new ConcurrentHashMap<>();
    
    // Settings
    private static boolean enabled = true;
    private static boolean detailedLogging = false;
    private static long slowOperationThreshold = 10_000_000; // 10ms in nanoseconds
    
    /**
     * Enable or disable performance profiling
     */
    public static void setEnabled(boolean enabled) {
        PerformanceProfiler.enabled = enabled;
        LOGGER.info("Performance profiling {}", enabled ? "ENABLED" : "DISABLED");
    }
    
    /**
     * Enable or disable detailed logging
     */
    public static void setDetailedLogging(boolean enabled) {
        detailedLogging = enabled;
        LOGGER.info("Detailed performance logging {}", enabled ? "ENABLED" : "DISABLED");
    }
    
    /**
     * Set threshold for slow operation warnings (in milliseconds)
     */
    public static void setSlowOperationThreshold(long thresholdMs) {
        slowOperationThreshold = thresholdMs * 1_000_000; // Convert to nanoseconds
        LOGGER.info("Slow operation threshold set to {}ms", thresholdMs);
    }
    
    /**
     * Start timing an operation
     */
    public static void startTiming(String operationName) {
        if (!enabled) return;
        
        String threadKey = operationName + "_" + Thread.currentThread().getId();
        activeTimers.put(threadKey, System.nanoTime());
        
        if (detailedLogging) {
            LOGGER.debug("⏱️ Started timing: {} [Thread: {}]", operationName, Thread.currentThread().getName());
        }
    }
    
    /**
     * End timing an operation and record the result
     */
    public static long endTiming(String operationName) {
        if (!enabled) return 0;
        
        long endTime = System.nanoTime();
        String threadKey = operationName + "_" + Thread.currentThread().getId();
        Long startTime = activeTimers.remove(threadKey);
        
        if (startTime == null) {
            LOGGER.warn("⚠️ No start time found for operation: {} [Thread: {}]", 
                operationName, Thread.currentThread().getName());
            return 0;
        }
        
        long duration = endTime - startTime;
        recordTiming(operationName, duration);
        
        // Log slow operations
        if (duration > slowOperationThreshold) {
            LOGGER.warn("🐌 SLOW OPERATION: {} took {:.2f}ms [Thread: {}]", 
                operationName, duration / 1_000_000.0, Thread.currentThread().getName());
        }
        
        if (detailedLogging) {
            LOGGER.debug("⏱️ Completed timing: {} - {:.2f}ms", operationName, duration / 1_000_000.0);
        }
        
        return duration;
    }
    
    /**
     * Record a timing measurement
     */
    private static void recordTiming(String operationName, long duration) {
        totalTimes.computeIfAbsent(operationName, k -> new AtomicLong(0)).addAndGet(duration);
        callCounts.computeIfAbsent(operationName, k -> new AtomicInteger(0)).incrementAndGet();
        
        // Update max time
        maxTimes.computeIfAbsent(operationName, k -> new AtomicLong(0))
            .updateAndGet(current -> Math.max(current, duration));
        
        // Update min time
        minTimes.computeIfAbsent(operationName, k -> new AtomicLong(Long.MAX_VALUE))
            .updateAndGet(current -> Math.min(current, duration));
    }
    
    /**
     * Time a runnable operation
     */
    public static void timeOperation(String operationName, Runnable operation) {
        startTiming(operationName);
        try {
            operation.run();
        } finally {
            endTiming(operationName);
        }
    }
    
    /**
     * Time a supplier operation and return its result
     */
    public static <T> T timeOperation(String operationName, java.util.function.Supplier<T> operation) {
        startTiming(operationName);
        try {
            return operation.get();
        } finally {
            endTiming(operationName);
        }
    }
    
    /**
     * Log performance statistics
     */
    public static void logStatistics() {
        if (!enabled) {
            LOGGER.info("Performance profiling is disabled");
            return;
        }
        
        LOGGER.info("📊 PERFORMANCE STATISTICS:");
        LOGGER.info("=" + "=".repeat(60));
        
        if (totalTimes.isEmpty()) {
            LOGGER.info("No performance data collected yet");
            return;
        }
        
        // Sort operations by total time
        totalTimes.entrySet().stream()
            .sorted((e1, e2) -> Long.compare(e2.getValue().get(), e1.getValue().get()))
            .forEach(entry -> {
                String operation = entry.getKey();
                long totalTime = entry.getValue().get();
                int calls = callCounts.get(operation).get();
                long maxTime = maxTimes.get(operation).get();
                long minTime = minTimes.get(operation).get();
                
                double avgTime = (double) totalTime / calls;
                
                LOGGER.info("🔍 {}", operation);
                LOGGER.info("  - Total: {:.2f}ms | Calls: {} | Avg: {:.2f}ms", 
                    totalTime / 1_000_000.0, calls, avgTime / 1_000_000.0);
                LOGGER.info("  - Min: {:.2f}ms | Max: {:.2f}ms", 
                    minTime / 1_000_000.0, maxTime / 1_000_000.0);
            });
        
        // Calculate total overhead
        long totalOverhead = totalTimes.values().stream()
            .mapToLong(AtomicLong::get)
            .sum();
        
        LOGGER.info("📈 SUMMARY:");
        LOGGER.info("  - Total Operations: {}", totalTimes.size());
        LOGGER.info("  - Total Overhead: {:.2f}ms", totalOverhead / 1_000_000.0);
        LOGGER.info("  - Active Timers: {}", activeTimers.size());
    }
    
    /**
     * Reset all performance statistics
     */
    public static void reset() {
        totalTimes.clear();
        callCounts.clear();
        maxTimes.clear();
        minTimes.clear();
        activeTimers.clear();
        LOGGER.info("🔄 Performance statistics reset");
    }
    
    /**
     * Get performance summary for a specific operation
     */
    public static String getOperationSummary(String operationName) {
        if (!totalTimes.containsKey(operationName)) {
            return "No data for operation: " + operationName;
        }
        
        long totalTime = totalTimes.get(operationName).get();
        int calls = callCounts.get(operationName).get();
        long maxTime = maxTimes.get(operationName).get();
        long minTime = minTimes.get(operationName).get();
        double avgTime = (double) totalTime / calls;
        
        return String.format("%s: %d calls, %.2fms avg (%.2f-%.2fms range)", 
            operationName, calls, avgTime / 1_000_000.0, 
            minTime / 1_000_000.0, maxTime / 1_000_000.0);
    }
    
    /**
     * Check if any operations are currently being timed
     */
    public static void checkForLeakedTimers() {
        if (!activeTimers.isEmpty()) {
            LOGGER.warn("⚠️ {} leaked timers detected:", activeTimers.size());
            activeTimers.forEach((key, startTime) -> {
                long duration = System.nanoTime() - startTime;
                LOGGER.warn("  - {}: running for {:.2f}ms", key, duration / 1_000_000.0);
            });
        }
    }
}