package com.example.metricsproducer.service;

import com.example.core.event.MetricsEvent;

import java.util.List;

public interface MetricsService {
    List<MetricsEvent> getMetrics();
}
