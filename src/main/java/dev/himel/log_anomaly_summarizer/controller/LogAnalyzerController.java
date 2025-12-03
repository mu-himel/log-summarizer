package dev.himel.log_anomaly_summarizer.controller;

import dev.himel.log_anomaly_summarizer.dto.request.Log;
import dev.himel.log_anomaly_summarizer.service.LogSummarizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/summarize-logs")
public class LogAnalyzerController {

    private final LogSummarizerService logSummarizerService;

    @PostMapping
    public Map<String,Object> summarize(@RequestBody List<Log> req){
        return logSummarizerService.getSummary(req);
    }
}
