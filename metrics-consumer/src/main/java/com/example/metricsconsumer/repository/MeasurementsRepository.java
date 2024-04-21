package com.example.metricsconsumer.repository;

import com.example.metricsconsumer.model.Measurement;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Long> {
    @EntityGraph(attributePaths = "metric")
    List<Measurement> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to);

    @EntityGraph(attributePaths = "metric")
    List<Measurement> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to, PageRequest of);

    @EntityGraph(attributePaths = "metric")
    List<Measurement> findAllByCreatedAtBetweenAndMetricId(LocalDateTime from, LocalDateTime to, Long metricId);

    @EntityGraph(attributePaths = "metric")
    List<Measurement> findAllByCreatedAtBetweenAndMetricId(LocalDateTime from, LocalDateTime to, Long metricId, PageRequest of);
}
