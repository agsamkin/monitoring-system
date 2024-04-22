package com.example.metricsconsumer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetMeasurementDto {
    @Min(value = 1, message = "The metric id parameter must not be less than one")
    private Long metricId;

    @Min(value = 0, message = "The page parameter cannot be negative")
    private Integer page;

    @Min(value = 1, message = "The page size parameter must not be less than one")
    private Integer size;

    @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "The sort parameter can take one of two values: asc or desc")
    private String sort;
}
