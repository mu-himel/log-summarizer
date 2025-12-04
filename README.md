# AI Log-summarizer
## AI-powered log summarizer
The log summarizer service will receive a collection of logs. It will perform log analysis using LLM (Google's Gemini AI) and generate a human-readable summary, key errors, and recommendations

### 1. STEPS TO BUILD AND RUN USING DOCKER
Check your working directory, It should at the project's root directory, run the following commands to build a Docker image

To Build Docker Image 
```shell
docker build -t logsummarizer:latest --no-cache .
```
To Run Docker Container Using Created Image use following command, make sure [API_KEY] replaced by your actual API KEY, which you can get from Google ai studio
```shell
docker run -d --name=c-log-summarizer -p 8080:8080 -e GOOGLE_GEMINI_API_KEY=[API_KEY] logsummarizer:latest
```
To verify docker container running at 8080 port use following command
```shell
docker ps
```
or you can visit http://127.0.0.1:8080/actuator/health which will show application health status

### 2. REQUEST AND RESPONSE

We can use either curl or postman to execute the service

Using curl request
```shell
curl -X POST 'http://127.0.0.1:8080/summarize-logs' \
    -H 'Content-Type: application/json' \
    -d '
      [
        {"timestamp": "2025-10-15T10:00:05Z", "level": "ERROR", "service" : "payment-service", "message": "Database connection timed out after 3001ms"}
      ]
     '
```

## Request Payload
```json
[
    {"timestamp": "2025-10-15T10:00:05Z", "level": "ERROR", "service" : "payment-service", "message": "Database connection timed out after 3001ms"}
]
```

## Response
```json
{
    "summary": "The `payment-service` is experiencing a critical database connectivity issue. A single error log indicates that the service failed to establish a connection with its database, resulting in a timeout after approximately 3 seconds. This type of error typically points to a problem with the database server itself (e.g., it's down, overloaded, or unresponsive) or a network issue preventing communication between the payment service and the database. The impact is likely a complete failure of any payment-related operations that require database access.",
    "key_error_signatures": [
        "payment-service: Database connection timed out"
    ],
    "recommendation": "Immediately investigate the status and performance of the database server used by the `payment-service`. Check for high CPU/memory utilization, long-running queries, or deadlocks. Concurrently, verify network connectivity and firewall rules between the `payment-service` host and the database server to rule out any network-related blockages."
}
```
Using Postman
![image alt](https://upww.screenrec.com/images/f_Prvk6xX2dDAzqLYET97t1h3GOuJIRjnZ.png)

### ABOUT THE PROMPT
```
Analyze the following collection of JSON log entries. Identify the primary service affected, the nature of the error, and any cascading failures by recognizing common patterns across the timestamps.
Generate a single, structured JSON output containing:
A concise human-readable summary of potential root cause or key issues, synthesizing the information from all provided logs, identify or analyze based on (e.g., service affected, error frequency, resulting impact, recurring errors, unusual patterns, potential casual relationship).
A list of key_error_signatures (phrases or patterns representing the root cause and any related or downstream failures observed across the collection).
A specific recommendation for the immediate next investigative steps to resolve the identified issue.
and make sure output contains summary, key_error_signatures, and recommendation key
[serialized log entries]
```
