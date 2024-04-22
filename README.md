# Monitoring system 

A monitoring system to track the performance of various components of your application using Spring Kafka. 
This system includes Producer to send metrics, Consumer to process and analyze them, and REST API to view the metrics.

## How to use

1. Clone repository
2. Up Kafka cluster from /docker/docker-compose.yml 
3. Up Metrics Consumer service database from /metrics-consumer/docker/docker-compose.yml 
4. Run Metrics Producer service
5. Run Metrics Consumer service

## System architecture

### System components

1. Metrics producer service - applications that send messages to topics
2. Kafka brokers - servers responsible for storing and transmitting messages
3. Kafka topics - are the categories to which posts are published and from which they are read
4. Metrics consumer service - applications that read posts from threads

### Data flow

Metrics producer service tracks and collects application performance metrics using Spring Actuator
and send them on a set schedule to a Kafka "metrics-topic".

Metrics consumer service takes metrics from Kafka topics "metrics-topic" and saves them to the database.

### Kafka configuration

Kafka cluster:

Kafka cluster consists of 3 servers. Each Kafka server runs in a separate docker container.
The configuration of the cluster kafka is defined in the file /docker/docker-compose.yml.

Kafka producer:

```properties
acks = all 
replicas = 3 
min.insync.replicas = 1 
delivery.timeout.ms = 10000 
linger.ms = 0 
request.timeout.ms = 5000 
enable.idempotence = true 
max.in.flight.requests.per.connection - 5
``` 

Kafka consumer:

```properties
fixed-back-off.interval.ms = 3000
fixed-back-off.max-attempt = 3
```






