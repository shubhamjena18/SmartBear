package com.smartbear.time.spokentime.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class CacheClearServiceTest {

    private CacheManager cacheManager;
    private CacheClearService cacheClearService;

    @BeforeEach
    void setUp() {
        cacheManager = Mockito.mock(CacheManager.class);
        cacheClearService = new CacheClearService(cacheManager);
    }

    @Test
    void testClearAllCaches() {
        Cache cache1 = Mockito.mock(Cache.class);
        Cache cache2 = Mockito.mock(Cache.class);

        when(cacheManager.getCacheNames()).thenReturn(Arrays.asList("cache1", "cache2"));
        when(cacheManager.getCache("cache1")).thenReturn(cache1);
        when(cacheManager.getCache("cache2")).thenReturn(cache2);

        cacheClearService.clearAllCaches();

        verify(cache1, times(1)).clear();
        verify(cache2, times(1)).clear();
    }

    @Test
    void testClearSpecificCache() {
        Cache cache = Mockito.mock(Cache.class);
        when(cacheManager.getCache("timeCache")).thenReturn(cache);

        cacheClearService.clearCacheByName("timeCache");

        verify(cache, times(1)).clear();
    }

    @Test
    void testClearSpecificCache_NotFound() {
        when(cacheManager.getCache("nonExistent")).thenReturn(null);

        cacheClearService.clearCacheByName("nonExistent");

        // No exception, nothing to clear
        verifyNoInteractions(Mockito.mock(Cache.class));
    }
}
