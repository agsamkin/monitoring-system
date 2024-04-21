package com.example.metricsproducer.service.impl;

import com.example.core.event.MetricsEvent;

import com.example.metricsproducer.api.ActuatorAPI;
import com.example.metricsproducer.service.MetricsService;

import lombok.RequiredArgsConstructor;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class MetricsServiceImpl implements MetricsService {

    private final Environment environment;
    private final ActuatorAPI actuatorAPI;

    @Override
    public List<MetricsEvent> getMetrics() {
        List<MetricsEvent> metrics = new ArrayList<>();

        String metricProps = environment.getProperty("application.metrics");
        String[] metricNames = Objects.isNull(metricProps) ? new String[] {} : metricProps.split(",");

        for (String name : metricNames) {
            var response = actuatorAPI.getMetrics(name);
            var measurements = response.measurements();
            for (var measurement : measurements) {
                MetricsEvent metricsEvent = MetricsEvent.builder()
                        .name(response.name())
                        .description(response.description())
                        .baseUnit(response.baseUnit())
                        .statistic(measurement.statistic())
                        .value(measurement.value()).build();
                metrics.add(metricsEvent);
            }
        }

        return metrics;
    }

}
