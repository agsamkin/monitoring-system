package com.example.metricsconsumer.handler;

import com.example.core.event.MetricsEvent;

import com.example.metricsconsumer.exception.custom.RetryableException;
import com.example.metricsconsumer.model.Metric;
import com.example.metricsconsumer.model.MetricInfo;
import com.example.metricsconsumer.service.MetricsService;

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

    private final MetricsService metricsService;

    @KafkaHandler
    public void handle(MetricsEvent metricsEvent) {

        MetricInfo metricInfo = MetricInfo.builder()
                .name(metricsEvent.getName())
                .description(metricsEvent.getDescription())
                .baseUnit(metricsEvent.getBaseUnit())
                .build();

        Metric metric = Metric.builder()
                .metricInfo(metricInfo)
                .statistic(metricsEvent.getStatistic())
                .value(metricsEvent.getValue()).build();

        try {
            log.info("Received event: {}", metricsEvent.getName());
            metricsService.saveMetric(metric);
        } catch (Throwable e) {
            log.error(e.getMessage());
            throw new RetryableException(e);
        }

    }

}
