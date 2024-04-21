# Core service 

The service contains the structure of events used in the exchange via Kafka.

## Metrics event structure

For example:
```json
{
  "name": "jvm.memory.used",
  "description": "The amount of used memory",
  "baseUnit": "bytes",
  "statistic": "VALUE",
  "value": 124278152 
}
```

