package com.example.metricsconsumer.service.impl;

import com.example.metricsconsumer.dto.GetMeasurementDto;
import com.example.metricsconsumer.dto.MeasurementDto;
import com.example.metricsconsumer.dto.MetricDto;
import com.example.metricsconsumer.model.Measurement;
import com.example.metricsconsumer.model.Metric;

import com.example.metricsconsumer.repository.MeasurementsRepository;

import com.example.metricsconsumer.service.MeasurementsService;
import com.example.metricsconsumer.service.MetricsService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class MeasurementsServiceImpl implements MeasurementsService {

    public static final int PAGE_DEFAULT = 0;
    public static final int PAGE_SIZE_DEFAULT = 10;

    public static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;
    public static final String DEFAULT_SORT_PROPERTY = "createdAt";

    private final MeasurementsRepository measurementsRepository;
    private final MetricsService metricsService;

    @Transactional(readOnly = true)
    @Override
    public List<MeasurementDto> getMeasurements(LocalDateTime from, LocalDateTime to) {
        return measurementsRepository.findAllByCreatedAtBetween(from, to)
                .stream()
                .map(this::mapToDto)
                .sorted(Comparator.comparing(MeasurementDto::getCreatedAt))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<MeasurementDto> getMeasurements(LocalDateTime from, LocalDateTime to, GetMeasurementDto params) {

        Long metricId = null;
        if (Objects.nonNull(params.getMetricId())) {
            metricId = params.getMetricId();
        }

        PageRequest of = null;
        if (Objects.nonNull(params.getPage())
                || Objects.nonNull(params.getSize())
                || Objects.nonNull(params.getSort())) {

            of = PageRequest.of(
                    Objects.isNull(params.getPage()) ? PAGE_DEFAULT : params.getPage(),
                    Objects.isNull(params.getSize())  ? PAGE_SIZE_DEFAULT : params.getSize(),
                    Sort.by(
                            Objects.isNull(params.getSort()) ? DEFAULT_SORT_DIRECTION :
                                    Sort.Direction.valueOf(params.getSort().toUpperCase(Locale.ROOT)), DEFAULT_SORT_PROPERTY));

        }

        List<MeasurementDto> measurements;

        if (Objects.nonNull(metricId) && Objects.nonNull(of)) {
            measurements = measurementsRepository.findAllByCreatedAtBetweenAndMetricId(from, to, metricId, of)
                    .stream()
                    .map(this::mapToDto)
                    .sorted(Comparator.comparing(MeasurementDto::getCreatedAt))
                    .collect(Collectors.toList());
        } else if (Objects.nonNull(of)) {
            measurements = measurementsRepository.findAllByCreatedAtBetween(from, to, of)
                    .stream()
                    .map(this::mapToDto)
                    .sorted(Comparator.comparing(MeasurementDto::getCreatedAt))
                    .collect(Collectors.toList());
        } else if (Objects.nonNull(metricId)) {
            measurements = measurementsRepository.findAllByCreatedAtBetweenAndMetricId(from, to, metricId)
                    .stream()
                    .map(this::mapToDto)
                    .sorted(Comparator.comparing(MeasurementDto::getCreatedAt))
                    .collect(Collectors.toList());
        } else {
            measurements = measurementsRepository.findAllByCreatedAtBetween(from, to)
                    .stream()
                    .map(this::mapToDto)
                    .sorted(Comparator.comparing(MeasurementDto::getCreatedAt))
                    .collect(Collectors.toList());
        }

        return measurements;

    }

    @Override
    public void saveMeasurement(Measurement measurement) {
        Metric metric = metricsService
                .getMetricByName(measurement.getMetric().getName())
                .orElse(measurement.getMetric());

        metric.addMeasurement(measurement);
        metricsService.saveMetric(metric);
    }

    private MeasurementDto mapToDto(Measurement measurement) {
        return MeasurementDto.builder()
                .metric(mapToDto(measurement.getMetric()))
                .statistic(measurement.getStatistic())
                .value(measurement.getValue())
                .createdAt(measurement.getCreatedAt()).build();
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
