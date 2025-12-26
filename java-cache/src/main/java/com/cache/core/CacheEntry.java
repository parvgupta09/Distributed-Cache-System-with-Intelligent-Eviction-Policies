package com.cache.core;

public class CacheEntry {
    public final String key;
    public String value;
    public long expiryTime;
    public int frequency;

    public CacheEntry(String key, String value, long ttlMillis) {
        this.key = key;
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + ttlMillis;
        this.frequency = 1;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}
