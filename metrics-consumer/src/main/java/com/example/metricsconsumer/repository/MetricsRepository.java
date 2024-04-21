package com.example.metricsconsumer.repository;

import com.example.metricsconsumer.model.Metric;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetricsRepository extends JpaRepository<Metric, Long>  {
    Optional<Metric> findByName(String name);
}
