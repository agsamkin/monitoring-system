package com.example.core.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MetricsEvent {
    private String name;
    private String description;
    private String baseUnit;
    private String statistic;
    private Double value;
}
