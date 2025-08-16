package com.smartbear.time.spokentime.service;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheClearService {

    private final CacheManager cacheManager;

    public CacheClearService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Clear all caches
     */
    public void clearAllCaches() {
        cacheManager.getCacheNames().forEach(name -> {
            if (cacheManager.getCache(name) != null) {
                cacheManager.getCache(name).clear();
            }
        });
    }

    /**
     * Clear a specific cache by name
     */
    public void clearCacheByName(String cacheName) {
        if (cacheManager.getCache(cacheName) != null) {
            cacheManager.getCache(cacheName).clear();
        }
    }
}
