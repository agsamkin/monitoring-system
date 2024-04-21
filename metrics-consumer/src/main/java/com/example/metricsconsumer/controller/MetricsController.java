package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.dto.MetricDto;
import com.example.metricsconsumer.service.MetricsService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.metricsconsumer.controller.MetricsController.METRICS_CONTROLLER_PATH;

@RequiredArgsConstructor
@RestController
@RequestMapping("${base-url}" + METRICS_CONTROLLER_PATH)
public class MetricsController {

    public static final String METRICS_CONTROLLER_PATH = "/metrics";
    public static final String ID = "/{id}";

    private final MetricsService metricsService;

    @GetMapping(ID)
    public MetricDto getMetricById(@PathVariable("id") long id) {
        return metricsService.getMetricById(id);
    }

    @GetMapping
    public List<MetricDto> getAllMetrics() {
        return metricsService.getAllMetrics();
    }

}
