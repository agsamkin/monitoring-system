# Metrics producer service 

The service tracks and collects application performance metrics using Spring Actuator
and sends them to a Kafka "metrics-topic" on a scheduled basis.

The composition of metrics can be customized in the "application.metrics" property 
in [/src/main/resources/config/application.yml](src/main/resources/config/application.yml) file.

For example:

```yml
application:
  metrics: application.ready.time,jvm.memory.max,process.uptime
```

All available metrics can be viewed at [http://[host]:[port]/actuator/metrics]().