//package com.smartbear.time.spokentime;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class SpokentimeApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}

package com.smartbear.time.spokentime;

import com.smartbear.time.spokentime.controller.TimeController;
import com.smartbear.time.spokentime.service.TimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpokentimeApplicationTests {

    @Autowired
    private TimeService timeService;

    @Autowired
    private TimeController timeController;

    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void contextLoads() {
        // Just ensures application context loads successfully
    }

    @Test
    void beansAreLoaded() {
        assertThat(timeService).isNotNull();
        assertThat(timeController).isNotNull();
        assertThat(reactiveRedisTemplate).isNotNull();
        assertThat(cacheManager).isNotNull();
    }
}

