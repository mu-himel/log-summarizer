package dev.himel.log_anomaly_summarizer.constant;

public class PromptTemplate {
    public static final String LOG_SUMMARIZER="""
                        Analyze the following collection of JSON log entries. Identify the primary service affected, the nature of the error, and any cascading failures by recognizing common patterns across the timestamps.
                        Generate a single, structured JSON output containing:
                        A concise human-readable summary of potential root cause or key issues, synthesizing the information from all provided logs, identify or analyze based on (e.g., service affected, error frequency, resulting impact, recurring errors, unusual patterns, potential casual relationship).
                        A list of key_error_signatures (phrases or patterns representing the root cause and any related or downstream failures observed across the collection).
                        A specific recommendation for the immediate next investigative steps to resolve the identified issue.
                        and make sure output contains summary, key_error_signatures, and recommendation key
                        """;
}
