# Monitoring system 

A monitoring system to track the performance of various components of your application using Spring Kafka. 
This system includes Producer to send metricInfos, Consumer to process and analyze them, and REST API to view the metricInfos.

### How to use

1. Clone repository
2. Up Kafka cluster from /docker/docker-compose.yml 
3. Up Metrics Consumer service database from /metricInfos-consumer/docker/docker-compose.yml 
4. Run Metrics Producer service
5. Run Metrics Consumer service

