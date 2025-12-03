package dev.himel.log_anomaly_summarizer.dto.request;

import dev.himel.log_anomaly_summarizer.dto.request.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPayload {
    private List<Log> logs;
}
