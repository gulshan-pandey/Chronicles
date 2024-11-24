package com.app.Chronicles.service;

import com.app.Chronicles.constants.Placeholders;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.constructor.Construct;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class AIService {



    @Value("${MISTRAL_API_KEY}")
    private String apiKey;

            public String analyzeText(String journals) throws Exception {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)  // Time to connect to the server
                        .writeTimeout(30, TimeUnit.SECONDS)   // Time to send the data
                        .readTimeout(60, TimeUnit.SECONDS)    // Time to read the server's response
                        .build();


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

                // Log the request body for debugging
                System.out.println("Request Body: " + requestBody.toString());
        // Step 3: Build HTTP request
        Request request = new Request.Builder()
                .url(Placeholders.MISTRAL_API_URL)
                .post(RequestBody.create(requestBody.toString(), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Accept", "application/json")
                .build();

        // Step 4: Execute request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorBody = response.body().string();
                throw new Exception("Request failed: " + response.code() + " - " + response.message() + " - " + errorBody);
            }


            // Step 5: Parse and return response
            String responseBody = response.body().string();

            JSONObject jsonResponse = new JSONObject(responseBody);
            String content = jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

            return content;
        } catch (IOException e) {
            throw new Exception("Network error: " + e.getMessage(), e);
        }
    }
}