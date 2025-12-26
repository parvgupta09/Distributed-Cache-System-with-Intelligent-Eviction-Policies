package com.cache.core;

public class CacheMetrics {
    public long hits = 0;
    public long misses = 0;
    public long evictions = 0;
    public long ttlExpired = 0;

    public double hitRate() {
        long total = hits + misses;
        return total == 0 ? 0.0 : (double) hits / total;
    }
}
