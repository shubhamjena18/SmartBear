package com.smartbear.time.spokentime.controller;

import com.smartbear.time.spokentime.service.CacheClearService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final CacheClearService cacheClearService;

    public CacheController(CacheClearService cacheClearService) {
        this.cacheClearService = cacheClearService;
    }

    // Clear all caches
    @DeleteMapping("/clearAll")
    public String clearAllCaches() {
        cacheClearService.clearAllCaches();
        return " All caches cleared!";
    }

    // Clear a specific cache by name
    @DeleteMapping("/clear/{cacheName}")
    public String clearCache(@PathVariable String cacheName) {
        cacheClearService.clearCacheByName(cacheName);
        return " Cache cleared: " + cacheName;
    }
}
