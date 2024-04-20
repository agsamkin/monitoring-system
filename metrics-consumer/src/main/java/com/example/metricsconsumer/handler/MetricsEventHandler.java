package com.example.metricsconsumer.handler;

import com.example.core.event.MetricsEvent;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@KafkaListener(topics = "${application.kafka.metrics-topic}")
@Component
public class MetricsEventHandler {

    @KafkaHandler
    public void handle(MetricsEvent metricsEvent) {
        log.info("Received event: {}", metricsEvent.getName());
    }

}
