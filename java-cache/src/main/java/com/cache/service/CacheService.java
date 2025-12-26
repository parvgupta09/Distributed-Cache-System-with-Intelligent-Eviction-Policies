package com.cache.service;

import com.cache.core.*;
import com.cache.eviction.*;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    private final ConcurrentHashMap<String, CacheEntry> store = new ConcurrentHashMap<>();
    private final EvictionPolicy eviction;
    private final CacheMetrics metrics = new CacheMetrics();
    private final int CAPACITY = 100;

    public CacheService() {

        boolean USE_ADAPTIVE = true;
        boolean USE_LFU = false;

        if (USE_ADAPTIVE) {
            this.eviction = new AdaptiveCache();
        } else if (USE_LFU) {
            this.eviction = new LFUCache();
        } else {
            this.eviction = new LRUCache();
        }
    }

    public synchronized String get(String key) {
        CacheEntry e = store.get(key);
        if (e == null || e.isExpired()) {
            metrics.misses++;
            store.remove(key);
            return null;
        }
        metrics.hits++;

        // Adaptive behavior only if using AdaptiveCache
        if (eviction instanceof AdaptiveCache adaptive) {
            adaptive.switchPolicy(metrics.hitRate());
        }

        return e.value;
    }

    public synchronized void put(String key, String value, long ttl) {
        if (store.size() >= CAPACITY) {
            String evict = eviction.evictKey();
            store.remove(evict);
            metrics.evictions++;
        }

        CacheEntry entry = new CacheEntry(key, value, ttl);
        store.put(key, entry);
        eviction.put(key, entry);
    }

    public CacheMetrics metrics() {
        return metrics;
    }
}
