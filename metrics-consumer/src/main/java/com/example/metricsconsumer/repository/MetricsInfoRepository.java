package com.example.metricsconsumer.repository;

import com.example.metricsconsumer.model.MetricInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetricsInfoRepository extends JpaRepository<MetricInfo, Long>  {
    Optional<MetricInfo> findByName(String name);
}
