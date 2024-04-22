package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.dto.MetricInfoDto;
import com.example.metricsconsumer.service.MetricsInfoService;

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

import static com.example.metricsconsumer.controller.MetricsInfoController.METRICS_INFO_CONTROLLER_PATH;

@Tag(name = "metrics-info-controller", description = "Metrics info controller")
@RequiredArgsConstructor
@RestController
@RequestMapping("${base-url}" + METRICS_INFO_CONTROLLER_PATH)
public class MetricsInfoController {

    public static final String METRICS_INFO_CONTROLLER_PATH = "/metrics-info";
    public static final String ID = "/{id}";

    private final MetricsInfoService metricsInfoService;

    @Operation(summary = "Get metric info by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metric info was found"),
            @ApiResponse(responseCode = "404", description = "Metric info with this id wasn`t found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(ID)
    public MetricInfoDto getMetricInfoById(@PathVariable("id") long id) {
        return metricsInfoService.getMetricInfoById(id);
    }

    @Operation(summary = "Get all metrics info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metrics info was found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    @GetMapping
    public List<MetricInfoDto> getAllMetricsInfo() {
        return metricsInfoService.getAllMetricsInfo();
    }

}
