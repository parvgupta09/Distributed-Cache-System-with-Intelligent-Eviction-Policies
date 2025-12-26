package com.cache.api;

import com.cache.service.CacheService;
import com.cache.core.CacheMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final CacheService service;

    public CacheController(CacheService service) {
        this.service = service;
    }

    @GetMapping("/{key}")
    public String get(@PathVariable String key) {
        return service.get(key);
    }

    @PostMapping("/{key}")
    public void put(
            @PathVariable String key,
            @RequestParam String value,
            @RequestParam long ttl
    ) {
        service.put(key, value, ttl);
    }

    @GetMapping("/metrics")
    public CacheMetrics metrics() {
        return service.metrics();
    }
}
