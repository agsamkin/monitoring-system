package com.example.metricsconsumer.service;

import com.example.metricsconsumer.dto.MetricInfoDto;
import com.example.metricsconsumer.model.MetricInfo;

import java.util.List;
import java.util.Optional;

public interface MetricsInfoService {
    Optional<MetricInfo> getMetricInfoByName(String name);

    MetricInfoDto getMetricInfoById(long id);
    List<MetricInfoDto> getAllMetricsInfo();

    void saveMetricInfo(MetricInfo metricInfo);
}
