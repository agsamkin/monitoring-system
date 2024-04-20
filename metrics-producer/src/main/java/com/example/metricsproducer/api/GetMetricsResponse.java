package com.example.metricsproducer.api;

import java.util.List;

public interface GetMetricsResponse {
    record Response(String name, String description, String baseUnit, List<Measurements> measurements) {}
    record Measurements(String statistic, Double value) {}
}
