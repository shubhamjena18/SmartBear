package com.smartbear.time.spokentime.controller;


import com.smartbear.time.spokentime.service.CacheClearService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(CacheController.class)
class CacheControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public CacheClearService cacheClearService() {
            return Mockito.mock(CacheClearService.class);
        }
    }

    @Autowired
    private CacheClearService cacheClearService;

    @Test
    void testClearAllCaches() {
        webTestClient.delete()
                .uri("/api/cache/clearAll")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(" All caches cleared!");

        Mockito.verify(cacheClearService, Mockito.times(1)).clearAllCaches();
    }
}

