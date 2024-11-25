package com.app.Chronicles.service;
import com.app.Chronicles.constants.Placeholders;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;



@Service
public class AIService {

    @Value("${MISTRAL_API_KEY}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate ;

    public String analyzeText(String journals) throws Exception {


        // Construct the prompt
        String prompt = Placeholders.AiPrompt + String.join("\n", journals);

        // Create JSON request body
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);

        JSONArray messagesArray = new JSONArray();
        messagesArray.put(userMessage);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "open-mistral-7b");
        requestBody.put("messages", messagesArray);

        // Log the request body
        System.out.println("Request Body: " + requestBody.toString());

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Create HttpEntity with request body and headers
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        try {
            // Send the POST request
            ResponseEntity<String> response = restTemplate.exchange(
                    Placeholders.MISTRAL_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            // Check response status
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new Exception("Request failed: " + response.getStatusCode() + " - " + response.getBody());
            }

            // Parse the response body
            JSONObject jsonResponse = new JSONObject(response.getBody());

            return jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");                      // returning the specific message to the email

        } catch (Exception e) {
            throw new Exception("Error during API call: " + e.getMessage(), e);
        }
    }
}

