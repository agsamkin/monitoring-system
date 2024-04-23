package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.dto.MetricDto;
import com.example.metricsconsumer.service.MetricsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.example.metricsconsumer.controller.MetricsController.METRICS_CONTROLLER_PATH;

@Tag(name = "metrics-controller", description = "Metrics controller")
@RequiredArgsConstructor
@RestController
@RequestMapping("${base-url}" + METRICS_CONTROLLER_PATH)
public class MetricsController {

    public static final String METRICS_CONTROLLER_PATH = "/metrics";

    private final MetricsService metricsService;

    @Operation(summary = "Get metrics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metrics was found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public List<MetricDto> getAllMetrics(
            @RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(value ="to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestParam(value = "metricInfoId", required = false) Long metricInfoId) {

        if (Objects.isNull(metricInfoId)) {
            return metricsService.getMetrics(from, to);
        } else {
            return metricsService.getMetricsByMetricInfoId(from, to, metricInfoId);
        }
    }

}
