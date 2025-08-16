package com.smartbear.time.spokentime.controller;



import com.smartbear.time.spokentime.service.TimeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/time")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/v1/britishtime")
    public Mono<String> getBritishTime(@RequestParam String time) {
        try {
            String[] parts = time.split(":");
            if (parts.length != 2) {
                return Mono.error(new IllegalArgumentException("Invalid time format. Use HH:mm"));
            }

            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            return timeService.getBritishTime(hour, minute);
        } catch (NumberFormatException e) {
            return Mono.error(new IllegalArgumentException("Invalid numeric values in time. Use HH:mm"));
        }
    }
}
