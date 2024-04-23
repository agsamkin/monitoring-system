package com.example.metricsconsumer.repository;

import com.example.metricsconsumer.model.Metric;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.util.List;

@Repository
public interface MetricsRepository extends JpaRepository<Metric, Long> {
    @EntityGraph(attributePaths = "metricInfo")
    List<Metric> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to);

    @EntityGraph(attributePaths = "metricInfo")
    List<Metric> findAllByCreatedAtBetweenAndMetricInfoId(LocalDateTime from, LocalDateTime to, long metricInfoId);
}
