package com.cache.eviction;

import com.cache.core.*;
import java.util.*;

public class LFUCache implements EvictionPolicy {
    private final Map<String, CacheEntry> map = new HashMap<>();

    public synchronized void put(String key, CacheEntry entry) {
        map.put(key, entry);
    }

    public synchronized CacheEntry get(String key) {
        CacheEntry e = map.get(key);
        if (e != null) e.frequency++;
        return e;
    }

    public synchronized void remove(String key) {
        map.remove(key);
    }

    public synchronized String evictKey() {
        return map.entrySet()
                .stream()
                .min(Comparator.comparingInt(e -> e.getValue().frequency))
                .get()
                .getKey();
    }
}
