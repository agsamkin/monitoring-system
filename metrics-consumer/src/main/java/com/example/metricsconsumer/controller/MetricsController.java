package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.dto.MetricDto;
import com.example.metricsconsumer.service.MetricsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.metricsconsumer.controller.MetricsController.METRICS_CONTROLLER_PATH;

@Tag(name = "metrics-controller", description = "Metrics controller")
@RequiredArgsConstructor
@RestController
@RequestMapping("${base-url}" + METRICS_CONTROLLER_PATH)
public class MetricsController {

    public static final String METRICS_CONTROLLER_PATH = "/metrics";
    public static final String ID = "/{id}";

    private final MetricsService metricsService;

    @Operation(summary = "Get metric by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metric was found"),
            @ApiResponse(responseCode = "404", description = "Metric with this id wasn`t found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(ID)
    public MetricDto getMetricById(@PathVariable("id") long id) {
        return metricsService.getMetricById(id);
    }

    @Operation(summary = "Get all metrics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metrics was found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    @GetMapping
    public List<MetricDto> getAllMetrics() {
        return metricsService.getAllMetrics();
    }

}
