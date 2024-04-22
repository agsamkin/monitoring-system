package com.example.metricsconsumer.service.impl;

import com.example.metricsconsumer.dto.MetricInfoDto;
import com.example.metricsconsumer.exception.custom.MetricInfoNotFoundException;
import com.example.metricsconsumer.model.MetricInfo;

import com.example.metricsconsumer.repository.MetricsInfoRepository;
import com.example.metricsconsumer.service.MetricsInfoService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class MetricsInfoServiceImpl implements MetricsInfoService {

    private final MetricsInfoRepository metricsInfoRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<MetricInfo> getMetricInfoByName(String name) {
        return metricsInfoRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public MetricInfoDto getMetricInfoById(long id) {
        MetricInfo metricInfo = metricsInfoRepository.findById(id)
                .orElseThrow(() -> new MetricInfoNotFoundException("Metric info not found by id: " + id));
        return mapToDto(metricInfo);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MetricInfoDto> getAllMetricsInfo() {
        List<MetricInfo> metricsInfo = metricsInfoRepository.findAll();
        return metricsInfo.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveMetricInfo(MetricInfo metricInfo) {
        metricsInfoRepository.save(metricInfo);
    }

    private MetricInfoDto mapToDto(MetricInfo metricInfo) {
        return MetricInfoDto.builder()
                .id(metricInfo.getId())
                .name(metricInfo.getName())
                .description(metricInfo.getDescription())
                .baseUnit(metricInfo.getBaseUnit())
                .createdAt(metricInfo.getCreatedAt()).build();
    }

}
