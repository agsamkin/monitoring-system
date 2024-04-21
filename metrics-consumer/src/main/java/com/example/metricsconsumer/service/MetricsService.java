package com.example.metricsconsumer.service;

import com.example.metricsconsumer.dto.MetricDto;
import com.example.metricsconsumer.model.Metric;

import java.util.List;
import java.util.Optional;

public interface MetricsService {
    Optional<Metric> getMetricByName(String name);

    MetricDto getMetricById(long id);
    List<MetricDto> getAllMetrics();

    void saveMetric(Metric metric);
}
