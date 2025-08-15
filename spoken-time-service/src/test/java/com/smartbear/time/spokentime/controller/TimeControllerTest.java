package com.smartbear.time.spokentime.controller;


import com.smartbear.time.spokentime.service.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class TimeControllerTest {

    private WebTestClient webTestClient;
    private TimeService timeService;

    @BeforeEach
    void setup() {
        timeService = Mockito.mock(TimeService.class);
        TimeController controller = new TimeController(timeService);

        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    void testGetBritishTime() {
        when(timeService.getBritishTime(anyInt(), anyInt()))
                .thenReturn(Mono.just("quarter past 3"));

        webTestClient.get()
                .uri("/api/time/v1/britishtime?hour=3&minute=15")  // Correct URL
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("quarter past 3");

    }
}
