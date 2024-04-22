# Metrics consumer service 

The service takes metrics from Kafka topics "metrics-topic" and saves them to the database.
The service provides a REST API for viewing metrics. 
API documentation is available by clicking here: [http://[host]:[port]/api-doc.html]().

### How to use

#### <u>Get metrics</u>

Request example:

```
GET http://localhost:8082/api/v1/metrics
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

#### <u>Get measurements</u>

Request example:

```
GET http://localhost:8082/api/v1/measurements?from=2024-04-01T00:00:00&to=2024-04-22T23:59:59
```

Optional parameters:

```json
{
    "metricId": 1,
    "page": 1,
    "size": 2,
    "sort": "desc"
}
```

Response example:

```json
[
  {
    "metric": {
      "id": 1,
      "name": "application.ready.time",
      "description": "Time taken for the application to be ready to service requests",
      "baseUnit": "seconds",
      "createdAt": "2024-04-21T12:59:44.02174"
    },
    "statistic": "VALUE",
    "value": 8.248,
    "createdAt": "2024-04-22T16:53:28.535052"
  },
  {
    "metric": {
      "id": 1,
      "name": "application.ready.time",
      "description": "Time taken for the application to be ready to service requests",
      "baseUnit": "seconds",
      "createdAt": "2024-04-21T12:59:44.02174"
    },
    "statistic": "VALUE",
    "value": 7.876,
    "createdAt": "2024-04-22T16:53:28.505553"
  }
]
```


