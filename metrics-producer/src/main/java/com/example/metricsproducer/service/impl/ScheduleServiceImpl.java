package com.example.metricsproducer.service.impl;

import com.example.core.event.MetricsEvent;

import com.example.metricsproducer.kafka.MetricsProducer;

import com.example.metricsproducer.service.MetricsService;
import com.example.metricsproducer.service.ScheduleService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    public static final int INITIAL_DELAY = 60000;
    public static final int FIXED_DELAY = 60000; // 300_000;

    private final MetricsService metricsService;
    private final MetricsProducer metricsProducer;

    @Scheduled(initialDelay = INITIAL_DELAY, fixedDelay = FIXED_DELAY)
    @Override
    public void sendMetrics() {
        List<MetricsEvent> events = metricsService.getMetrics();
        for (MetricsEvent event : events) {
            metricsProducer.send(event);
        }
    }

}
