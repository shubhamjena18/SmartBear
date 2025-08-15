package com.smartbear.time.spokentime.service;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class TimeService {

    private final Cache<String, String> caffeineCache;
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public TimeService(Caffeine<Object, Object> caffeine,
                       ReactiveRedisTemplate<String, String> redisTemplate) {
        this.caffeineCache = caffeine.build();
        this.redisTemplate = redisTemplate;
    }

    public Mono<String> getBritishTime(int hour, int minute) {
        String key = hour + ":" + minute;

        //  Check Caffeine
        String cached = caffeineCache.getIfPresent(key);
        if (cached != null) {
            return Mono.just(cached);
        }

        //  Check Redis
        return redisTemplate.opsForValue().get(key)
                .switchIfEmpty(
        // Compute the time if missing in redis and caffeine
                        Mono.fromSupplier(() -> convertToBritishTime(hour, minute))
                                .flatMap(value ->
                                        redisTemplate.opsForValue()
                                                .set(key, value, Duration.ofMinutes(10))
                                                .thenReturn(value)
                                )
                )
                .doOnNext(value -> caffeineCache.put(key, value)); // put in Caffeine
    }

    private String convertToBritishTime(int hour, int minute) {
        if (minute == 0) return hour + " o'clock";
        if (minute == 15) return "quarter past " + hour;
        if (minute == 30) return "half past " + hour;
        if (minute == 45) return "quarter to " + ((hour + 1) % 12);
        return minute + " past " + hour;
    }
}

