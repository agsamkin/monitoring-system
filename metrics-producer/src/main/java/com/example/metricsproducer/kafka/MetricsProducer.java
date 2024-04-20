package com.example.metricsproducer.kafka;

import com.example.core.event.MetricsEvent;
import com.example.metricsproducer.config.KafkaConfig;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MetricsProducer {

    private final KafkaConfig kafkaConfig;
    private final KafkaTemplate<String, MetricsEvent> kafkaTemplate;

    public void send(MetricsEvent metricsEvent) {
        kafkaTemplate.send(kafkaConfig.getMetricsTopic(), String.valueOf(metricsEvent.hashCode()), metricsEvent);
    }

}
