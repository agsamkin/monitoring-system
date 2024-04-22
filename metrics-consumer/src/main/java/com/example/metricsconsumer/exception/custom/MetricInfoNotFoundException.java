package com.example.metricsconsumer.exception.custom;

import jakarta.persistence.EntityNotFoundException;

public class MetricInfoNotFoundException extends EntityNotFoundException {
    public MetricInfoNotFoundException(String message) {
        super(message);
    }

    public MetricInfoNotFoundException(Exception cause) {
        super(cause);
    }
}
