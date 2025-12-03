package dev.himel.log_anomaly_summarizer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.himel.log_anomaly_summarizer.constant.PromptTemplate;
import dev.himel.log_anomaly_summarizer.dto.request.Log;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogSummarizerService {

    private final ChatClient client;

    public LogSummarizerService(ChatClient.Builder builder){
        this.client = builder.build();
    }
    public Map<String,Object> getSummary(List<Log> req){
        ObjectMapper objectMapper = new ObjectMapper();
        ChatResponse chatResponse = null;
        try {

            String logs = objectMapper.writeValueAsString(req);
            String prompt= PromptTemplate.LOG_SUMMARIZER + logs;
            System.out.println(prompt);
            chatResponse = client.prompt()
                    .user(prompt).call().chatResponse();


            String output = chatResponse.getResult().getOutput().getText()
                    .replaceAll("(?s)^```json\\s*", "")
                    .replaceAll("(?s)\\s*```$", "");

            return objectMapper.readValue(output, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
