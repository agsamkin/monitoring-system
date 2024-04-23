# Metrics consumer service

The service retrieves metrics from Kafka "metrics-topic" topics and stores them in a database.
The service provides a REST API for viewing metrics.
API documentation is available by clicking here: [http://[host]:[port]/api-doc.html]().

### How to use

#### <u>Get metrics info</u>

Request example:

```
GET http://localhost:8082/api/v1/metrics-info
```

Response example:

```json
[
    {
        "id": 1,
        "name": "application.ready.time",
        "description": "Time taken for the application to be ready to service requests",
        "baseUnit": "seconds",
        "createdAt": "2024-04-21T12:59:44.02174"
    },
    {
        "id": 2,
        "name": "process.uptime",
        "description": "The uptime of the Java virtual machine",
        "baseUnit": "seconds",
        "createdAt": "2024-04-21T12:59:44.096484"
    }
]
```

#### <u>Get metrics</u>

Request example:

```
http://localhost:8082/api/v1/metrics?from=2024-04-01T00:00:00&to=2024-04-30T23:59:59&metricInfoId=1
```

Response example:

```json
[
  {
    "name": "application.ready.time",
    "statistic": "VALUE",
    "value": 8.389,
    "createdAt": "2024-04-22T19:31:44.586525"
  },
  {
    "name": "application.ready.time",
    "statistic": "VALUE",
    "value": 7.851,
    "createdAt": "2024-04-22T19:36:11.205786"
  }
]
```


