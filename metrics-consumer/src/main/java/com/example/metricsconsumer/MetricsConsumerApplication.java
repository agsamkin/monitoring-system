package com.example.metricsconsumer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "Metrics Consumer API",
				description = "Metrics Consumer API", version = "v1.01")
)
@SpringBootApplication
public class MetricsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricsConsumerApplication.class, args);
	}

}
