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
    public Mono<String> getBritishTime(@RequestParam int hour, @RequestParam int minute) {
        return timeService.getBritishTime(hour, minute);
    }
}
