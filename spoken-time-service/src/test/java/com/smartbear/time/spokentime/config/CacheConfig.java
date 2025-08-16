package com.smartbear.time.spokentime.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CacheConfigTest {

    @Autowired
    private CacheManager caffeineCacheManager;

    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    @Test
    void testCaffeineCacheManagerLoaded() {
        assertThat(caffeineCacheManager).isNotNull();
    }

    @Test
    void testReactiveRedisTemplateLoaded() {
        assertThat(reactiveRedisTemplate).isNotNull();
    }
}
