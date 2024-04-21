package com.example.metricsconsumer.service;

import com.example.metricsconsumer.dto.GetMeasurementDto;
import com.example.metricsconsumer.dto.MeasurementDto;
import com.example.metricsconsumer.model.Measurement;

import java.time.LocalDateTime;
import java.util.List;

public interface MeasurementsService {
    List<MeasurementDto> getMeasurements(LocalDateTime from, LocalDateTime to);
    List<MeasurementDto> getMeasurements(LocalDateTime from, LocalDateTime to, GetMeasurementDto params);

    void saveMeasurement(Measurement measurement);
}
