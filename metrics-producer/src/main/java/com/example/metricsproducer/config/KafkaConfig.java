package com.example.metricsproducer.config;

import lombok.Getter;
import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Getter
@Configuration
public class KafkaConfig {

    @Value("${application.kafka.metrics-topic}")
    private String metricsTopic;

    @Bean
    public NewTopic metricsTopic() {
        return TopicBuilder.name(metricsTopic)
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2")).build();
    }

}
