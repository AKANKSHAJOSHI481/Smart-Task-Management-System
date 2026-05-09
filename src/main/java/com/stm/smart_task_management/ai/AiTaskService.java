package com.stm.smart_task_management.ai;

import com.stm.smart_task_management.ai.dto.TaskPriorityRequest;
import com.stm.smart_task_management.ai.dto.TaskPriorityResponse;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AiTaskService {
    private static final String GEMINI_API_BASE_URL = "https://generativelanguage.googleapis.com";

    private final RestClient.Builder restClientBuilder;

    @Value("${spring.ai.google.genai.api-key:}")
    private String geminiApiKey;

    @Value("${spring.ai.google.genai.chat.options.model:gemini-1.5-flash}")
    private String model;

    public TaskPriorityResponse prioritize(TaskPriorityRequest request){
        if (!StringUtils.hasText(geminiApiKey)) {
            throw new IllegalStateException(
                    "Gemini API key is missing. Set GEMINI_API_KEY or GOOGLE_API_KEY, or provide it in .env."
            );
        }

        String prompt = """
                You are a task prioritization assistant.
                
                Rules:
                - HIGH = production issues, security, customer impact
                - MEDIUM = important business work
                - LOW = cosmetic or non-urgent
                
                Return ONLY:
                
                PRIORITY: <LEVEL>
                REASON: <SHORT_REASON>
                
                Task Title:
                %s
                
                Task Description:
                %s
                """
                .formatted(
                        request.title(),
                        request.description()
                );

        RestClient restClient = restClientBuilder
                .baseUrl(GEMINI_API_BASE_URL)
                .build();

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        JsonNode response;
        try {
            response = restClient
                    .post()
                    .uri("/v1beta/models/{model}:generateContent", model)
                    .header("x-goog-api-key", geminiApiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(JsonNode.class);
        } catch (RestClientResponseException ex) {
            throw new IllegalStateException(
                    "Gemini request failed with status %s. Verify the API key and model access. Response: %s"
                            .formatted(ex.getStatusCode(), ex.getResponseBodyAsString()),
                    ex
            );
        } catch (RestClientException ex) {
            throw new IllegalStateException("Gemini request failed.", ex);
        }

        return parseResponse(extractText(response));

    }

    private TaskPriorityResponse parseResponse(String response){
        String priority = "MEDIUM";
        if(response.contains("HIGH")){
            priority = "HIGH";
        }
        else if(response.contains("LOW")){
            priority = "LOW";
        }
        return new TaskPriorityResponse(priority, response);
    }

    private String extractText(JsonNode response) {
        if (response == null) {
            throw new IllegalStateException("Gemini returned an empty response.");
        }

        JsonNode candidates = response.path("candidates");
        if (!candidates.isArray() || candidates.isEmpty()) {
            throw new IllegalStateException("Gemini returned no candidates: " + response);
        }

        StringBuilder text = new StringBuilder();
        JsonNode parts = candidates.get(0).path("content").path("parts");
        if (parts.isArray()) {
            for (JsonNode part : parts) {
                String value = part.path("text").asText();
                if (StringUtils.hasText(value)) {
                    if (!text.isEmpty()) {
                        text.append('\n');
                    }
                    text.append(value);
                }
            }
        }

        if (!StringUtils.hasText(text.toString())) {
            throw new IllegalStateException("Gemini response did not contain text: " + response);
        }

        return text.toString();
    }
}
