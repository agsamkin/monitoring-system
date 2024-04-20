package com.example.metricsproducer.api;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "actuator", url = "http://localhost:8081/actuator")
public interface ActuatorAPI {

    @GetMapping("/metrics/{name}")
    GetMetricsResponse.Response getMetrics(@PathVariable("name") String name);

}
