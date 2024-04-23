# Monitoring system 

A monitoring system for tracking the performance of various components of your application using Spring Actuator and Apache Kafka.
This system includes a metrics producer service to collect metrics, a metrics consumer service to store and analyze them, 
and a Kafka cluster to share events between services.

## How to use

1. Clone repository
2. Up Kafka cluster from [/docker/docker-compose.yml](docker/docker-compose.yml) 
3. Up Metrics Consumer service database from [/metrics-consumer/docker/docker-compose.yml](metrics-consumer/docker/docker-compose.yml) 
4. Run Metrics Producer service
5. Run Metrics Consumer service

## System architecture

### System components

1. __[Metrics producer service](metrics-producer/README.md)__ </br>
   The service tracks and collects application performance metrics using Spring Actuator
   and sends them to a Kafka "metrics-topic" on a scheduled basis.

2. __[Metrics consumer service](metrics-consumer/README.md)__ </br>
   The service retrieves' metrics from Kafka "metrics-topic" topics and stores them in a database.

3. __[Kafka](docker/docker-compose.yml)__ </br>
   Message broker for exchanging messages between services.

### Data flow

Metrics producer service tracks and collects application performance metrics using Spring Actuator
and sends them to a Kafka "metrics-topic" on a scheduled basis.

Metrics consumer service retrieves metrics from Kafka topics "metrics-topic" and stores them in the database.

### Configuration

The composition of metrics and Kafka producer parameters can be configured
in [/metrics-producer/src/main/resources/config/application.yml](metrics-producer/src/main/resources/config/application.yml) file.

The parameters of the Kafka consumer can be configured in the [/metrics-consumer/src/main/resources/application.yml](metrics-consumer/src/main/resources/application.yml) file.







