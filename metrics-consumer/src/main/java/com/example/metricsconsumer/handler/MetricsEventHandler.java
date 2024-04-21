package com.example.metricsconsumer.handler;

import com.example.core.event.MetricsEvent;

import com.example.metricsconsumer.exception.custom.RetryableException;
import com.example.metricsconsumer.model.Measurement;
import com.example.metricsconsumer.model.Metric;
import com.example.metricsconsumer.service.MeasurementsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@KafkaListener(topics = "${application.kafka.metrics-topic}")
@Component
public class MetricsEventHandler {

    private final MeasurementsService measurementsService;

    @KafkaHandler
    public void handle(MetricsEvent metricsEvent) {

        Metric metric = Metric.builder()
                .name(metricsEvent.getName())
                .description(metricsEvent.getDescription())
                .baseUnit(metricsEvent.getBaseUnit())
                .build();

        Measurement measurement = Measurement.builder()
                .metric(metric)
                .statistic(metricsEvent.getStatistic())
                .value(metricsEvent.getValue()).build();

        try {
            log.info("Received event: {}", metricsEvent.getName());
            measurementsService.saveMeasurement(measurement);
        } catch (Throwable e) {
            log.error(e.getMessage());
            throw new RetryableException(e);
        }

    }

}
