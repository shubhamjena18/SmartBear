package com.smartbear.time.spokentime.service;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TimeServiceTest {

    private TimeService timeService;
    private ReactiveRedisTemplate<String, String> redisTemplate;
    private ReactiveValueOperations<String, String> valueOps;

    @BeforeEach
    void setup() {
        redisTemplate = Mockito.mock(ReactiveRedisTemplate.class);
        valueOps = Mockito.mock(ReactiveValueOperations.class);
        when(redisTemplate.opsForValue()).thenReturn(valueOps);

        timeService = new TimeService(Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(5)), redisTemplate);
    }

    @Test
    void testComputeBritishTimeWhenNotCached() {
        String key = "3:15";

        when(valueOps.get(key)).thenReturn(Mono.empty());
        when(valueOps.set(key, "quarter past 3", Duration.ofMinutes(10))).thenReturn(Mono.just(true));

        Mono<String> result = timeService.getBritishTime(3, 15);

        StepVerifier.create(result)
                .expectNext("quarter past 3")
                .verifyComplete();

        verify(valueOps, times(1)).get(key);
        verify(valueOps, times(1)).set(key, "quarter past 3", Duration.ofMinutes(10));
    }

    @Test
    void testComputeBritishTimeWhenRedisCached() {
        String key = "4:0";

        when(valueOps.get(key)).thenReturn(Mono.just("4 o'clock"));

        Mono<String> result = timeService.getBritishTime(4, 0);

        StepVerifier.create(result)
                .expectNext("4 o'clock")
                .verifyComplete();

        verify(valueOps, times(1)).get(key);
        verify(valueOps, never()).set(anyString(), anyString(), any());
    }
}
