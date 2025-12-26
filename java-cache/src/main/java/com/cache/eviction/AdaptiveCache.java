package com.cache.eviction;

import com.cache.core.*;

public class AdaptiveCache implements EvictionPolicy {

    private final EvictionPolicy lru = new LRUCache();
    private final EvictionPolicy lfu = new LFUCache();
    private boolean useLFU = false;

    public void switchPolicy(double hitRate) {
        useLFU = hitRate < 0.3;
    }

    private EvictionPolicy active() {
        return useLFU ? lfu : lru;
    }

    public void put(String key, CacheEntry entry) {
        active().put(key, entry);
    }

    public CacheEntry get(String key) {
        return active().get(key);
    }

    public void remove(String key) {
        active().remove(key);
    }

    public String evictKey() {
        return active().evictKey();
    }
}
