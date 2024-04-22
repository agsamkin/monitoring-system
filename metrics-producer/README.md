# Metrics producer service 

The service tracks and collects application performance metricInfos using Spring Actuator 
and send them on a set schedule to a Kafka "metricInfos-topic".

The composition of metricInfos can be customized in the "application.metricInfos" property 
in /src/main/resources/config/application.yml file.

For example:

```yml
application:
  metricInfos: application.ready.time,jvm.memory.max,process.uptime
```

All available metricInfos can be viewed at [http://[host]:[port]/actuator/metricInfos]().