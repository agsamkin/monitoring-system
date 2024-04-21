package com.example.metricsconsumer.service.impl;

import com.example.metricsconsumer.dto.MetricDto;
import com.example.metricsconsumer.exception.custom.MetricNotFoundException;
import com.example.metricsconsumer.model.Metric;

import com.example.metricsconsumer.repository.MetricsRepository;
import com.example.metricsconsumer.service.MetricsService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class MetricsServiceImpl implements MetricsService {

    private final MetricsRepository metricsRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Metric> getMetricByName(String name) {
        return metricsRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public MetricDto getMetricById(long id) {
        Metric metric = metricsRepository.findById(id)
                .orElseThrow(() -> new MetricNotFoundException("Metric not found by id: " + id));
        return mapToDto(metric);
    }


    @Transactional(readOnly = true)
    @Override
    public List<MetricDto> getAllMetrics() {
        List<Metric> metrics = metricsRepository.findAll();
        return metrics.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveMetric(Metric metric) {
        metricsRepository.save(metric);
    }

    private MetricDto mapToDto(Metric metric) {
        return MetricDto.builder()
                .id(metric.getId())
                .name(metric.getName())
                .description(metric.getDescription())
                .baseUnit(metric.getBaseUnit())
                .createdAt(metric.getCreatedAt()).build();
    }

}
