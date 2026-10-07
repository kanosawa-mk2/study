package dev.jinmon.llm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/** OpenAI Responses API adapter. Game logic depends only on LlmClient. */
public class OpenAiResponsesClient implements LlmClient {
    private static final ObjectMapper JSON = new ObjectMapper();
    private final HttpClient http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(20)).build();
    private final String apiKey;
    private final String model;

    public OpenAiResponsesClient(String apiKey, String model) {
        if (apiKey == null || apiKey.isBlank()) throw new IllegalArgumentException("OPENAI_API_KEY is not configured");
        this.apiKey = apiKey;
        this.model = model == null || model.isBlank() ? "gpt-5-mini" : model;
    }

    @Override public LlmResponse generate(LlmRequest request) {
        try {
            ObjectNode body = JSON.createObjectNode();
            body.put("model", model);
            body.put("instructions", request.instructions());
            body.put("input", request.input());
            body.put("store", false);
            ObjectNode format = body.putObject("text").putObject("format");
            format.put("type", "json_schema").put("name", "suspect_reply").put("strict", true);
            format.putObject("schema").put("type", "object").put("additionalProperties", false)
                    .putArray("required").add("dialogue").add("emotion");
            ObjectNode schema = (ObjectNode) format.path("schema");
            ObjectNode properties = schema.putObject("properties");
            properties.putObject("dialogue").put("type", "string");
            properties.putObject("emotion").put("type", "string");

            HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://api.openai.com/v1/responses"))
                    .timeout(Duration.ofSeconds(90)).header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(JSON.writeValueAsString(body))).build();
            HttpResponse<String> response = http.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300)
                throw new IllegalStateException("LLM API returned HTTP " + response.statusCode());
            JsonNode root = JSON.readTree(response.body());
            for (JsonNode item : root.path("output")) for (JsonNode content : item.path("content")) {
                if (content.path("type").asText().equals("output_text")) {
                    JsonNode result = JSON.readTree(content.path("text").asText());
                    return new LlmResponse(result.path("dialogue").asText(), result.path("emotion").asText());
                }
            }
            throw new IllegalStateException("LLM response did not contain structured output");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("LLM request was interrupted");
        } catch (Exception e) {
            if (e instanceof IllegalStateException state) throw state;
            // Do not print or propagate request headers, which contain the API key.
            throw new IllegalStateException("Could not complete LLM request: " + e.getClass().getSimpleName());
        }
    }
}
