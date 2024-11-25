package com.app.Chronicles.service;

import com.app.Chronicles.constants.Placeholders;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;




@Service
public class AIService {

    @Value("${MISTRAL_API_KEY}")
    private String apiKey;

    private final WebClient webClient;

    // Constructor to initialize WebClient
    public AIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(Placeholders.MISTRAL_URL) // Base URL
                .build();
    }

    public String analyzeText(String journals) throws Exception {
        // Construct the Prompt
        String prompt = Placeholders.AiPrompt + String.join("\n", journals);

        // Create JSON request body
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);

        JSONArray messagesArray = new JSONArray();
        messagesArray.put(userMessage);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "open-mistral-7b");
        requestBody.put("messages", messagesArray); // Add messages array to the body


        System.out.println("Request Body: " + requestBody.toString());

        try {
            // Make the HTTP call using WebClient
            String response = webClient.post()
                    .uri("") // No path because baseUrl is already set
                    .header("Authorization", "Bearer " + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody.toString())
                    .retrieve()
                    .bodyToMono(String.class)               // Parse the response as a string
                    .block(); // Block for a synchronous call

            // Parse the response
            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        } catch (Exception e) {
            throw new Exception("Error occurred during API call: " + e.getMessage(), e);
        }
    }
}
