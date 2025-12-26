package com.cache.eviction;

import com.cache.core.*;
import java.util.*;

public class LRUCache implements EvictionPolicy {
    private final LinkedHashMap<String, CacheEntry> map =
            new LinkedHashMap<>(16, 0.75f, true);

    public synchronized void put(String key, CacheEntry entry) {
        map.put(key, entry);
    }

    public synchronized CacheEntry get(String key) {
        return map.get(key);
    }

    public synchronized void remove(String key) {
        map.remove(key);
    }

    public synchronized String evictKey() {
        return map.keySet().iterator().next();
    }
}
