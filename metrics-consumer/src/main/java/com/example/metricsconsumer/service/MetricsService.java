package com.example.metricsconsumer.service;

import com.example.metricsconsumer.dto.MetricDto;
import com.example.metricsconsumer.model.Metric;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricsService {
    List<MetricDto> getMetrics(LocalDateTime from, LocalDateTime to);
    List<MetricDto> getMetricsByMetricsInfoId(LocalDateTime from, LocalDateTime to, long metricsInfoId);

    void saveMetric(Metric metric);
}
