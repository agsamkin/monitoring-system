package com.example.metricsconsumer.service.impl;

import com.example.metricsconsumer.dto.MetricDto;
import com.example.metricsconsumer.model.Metric;
import com.example.metricsconsumer.model.MetricInfo;

import com.example.metricsconsumer.repository.MetricsRepository;

import com.example.metricsconsumer.service.MetricsService;
import com.example.metricsconsumer.service.MetricsInfoService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class MetricsServiceImpl implements MetricsService {

    private final MetricsRepository metricsRepository;
    private final MetricsInfoService metricsInfoService;

    @Transactional(readOnly = true)
    @Override
    public List<MetricDto> getMetrics(LocalDateTime from, LocalDateTime to) {
        return metricsRepository.findAllByCreatedAtBetween(from, to)
                .stream()
                .map(this::mapToDto)
                .sorted(Comparator.comparing(MetricDto::getCreatedAt))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<MetricDto> getMetricsByMetricInfoId(LocalDateTime from, LocalDateTime to, Long metricInfoId) {
        checkMetricInfoById(metricInfoId);

        return metricsRepository.findAllByCreatedAtBetweenAndMetricInfoId(from, to, metricInfoId)
                .stream()
                .map(this::mapToDto)
                .sorted(Comparator.comparing(MetricDto::getCreatedAt))
                .collect(Collectors.toList());
    }

    @Override
    public void saveMetric(Metric metric) {
        MetricInfo metricInfo = metricsInfoService
                .getMetricInfoByName(metric.getMetricInfo().getName())
                .orElse(metric.getMetricInfo());

        metricInfo.addMetric(metric);
        metricsInfoService.saveMetricInfo(metricInfo);
    }

    private void checkMetricInfoById(Long id) {
        metricsInfoService.getMetricInfoById(id);
    }

    private MetricDto mapToDto(Metric metric) {
        return MetricDto.builder()
                .name(metric.getMetricInfo().getName())
                .statistic(metric.getStatistic())
                .value(metric.getValue())
                .createdAt(metric.getCreatedAt()).build();
    }

}
