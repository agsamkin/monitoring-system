package com.example.metricsconsumer.exception.custom;

import jakarta.persistence.EntityNotFoundException;

public class MetricNotFoundException extends EntityNotFoundException {
    public MetricNotFoundException(String message) {
        super(message);
    }

    public MetricNotFoundException(Exception cause) {
        super(cause);
    }
}
