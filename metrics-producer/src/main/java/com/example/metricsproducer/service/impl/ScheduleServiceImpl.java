package com.example.metricsproducer.service.impl;

import com.example.core.event.MetricsEvent;

import com.example.metricsproducer.kafka.MetricsProducer;

import com.example.metricsproducer.service.MetricsService;
import com.example.metricsproducer.service.ScheduleService;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final int ONE_MIN = 60000;
    private static final int ONE_HOUR = 3600000;

    private final MetricsService metricsService;
    private final MetricsProducer metricsProducer;

//    @Scheduled(initialDelay = ONE_MIN * 5, fixedDelay = ONE_HOUR)
    @Scheduled(initialDelay = ONE_MIN, fixedDelay = ONE_MIN)
    @Override
    public void sendMetrics() {
        List<MetricsEvent> events = metricsService.getMetrics();
        for (MetricsEvent event : events) {
            metricsProducer.send(event);
        }
    }

}
