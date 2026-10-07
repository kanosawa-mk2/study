package dev.jinmon.llm;

public interface LlmClient {
    LlmResponse generate(LlmRequest request);
}
