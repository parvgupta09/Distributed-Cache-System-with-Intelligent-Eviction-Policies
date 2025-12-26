package com.cache.core;

public interface EvictionPolicy {
    void put(String key, CacheEntry entry);
    CacheEntry get(String key);
    void remove(String key);
    String evictKey();
}
