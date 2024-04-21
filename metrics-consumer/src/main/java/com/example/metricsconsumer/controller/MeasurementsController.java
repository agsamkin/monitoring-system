package com.example.metricsconsumer.controller;

import com.example.metricsconsumer.dto.GetMeasurementDto;
import com.example.metricsconsumer.dto.MeasurementDto;
import com.example.metricsconsumer.service.MeasurementsService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.example.metricsconsumer.controller.MeasurementsController.MEASUREMENTS_CONTROLLER_PATH;

@RequiredArgsConstructor
@RestController
@RequestMapping("${base-url}" + MEASUREMENTS_CONTROLLER_PATH)
public class MeasurementsController {

    public static final String MEASUREMENTS_CONTROLLER_PATH = "/measurements";

    private final MeasurementsService measurementsService;

    @GetMapping
    public List<MeasurementDto> getAllMeasurements(
            @RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(value ="to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestBody(required = false) @Valid GetMeasurementDto params) {

        if (Objects.nonNull(params)) {
            return measurementsService.getMeasurements(from, to, params);
        } else {
            return measurementsService.getMeasurements(from, to);
        }

    }

}
