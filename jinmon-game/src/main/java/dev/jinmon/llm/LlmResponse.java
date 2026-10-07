package dev.jinmon.llm;

public record LlmResponse(String dialogue, String emotion) {
    public LlmResponse {
        if (dialogue == null || dialogue.isBlank()) dialogue = "……少し考えさせてください。";
        if (emotion == null || emotion.isBlank()) emotion = "neutral";
    }
}
