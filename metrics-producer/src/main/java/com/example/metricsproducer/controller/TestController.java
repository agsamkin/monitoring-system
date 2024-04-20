package com.example.metricsproducer.controller;

import com.example.core.event.MetricsEvent;
import com.example.metricsproducer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class TestController {

    private final MetricsService metricsService;

    @GetMapping
    public List<MetricsEvent> getMetrics() {
        return metricsService.getMetrics();
    }

}
