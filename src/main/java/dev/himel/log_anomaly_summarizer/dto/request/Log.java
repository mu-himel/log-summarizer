package dev.himel.log_anomaly_summarizer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    private String timestamp;
    private String level;
    private String service;
    private String message;
}
