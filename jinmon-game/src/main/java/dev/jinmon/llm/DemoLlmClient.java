package dev.jinmon.llm;

/** APIキーなしでゲームを試すための簡単な応答実装。 */
public class DemoLlmClient implements LlmClient {
    @Override public LlmResponse generate(LlmRequest request) {
        String input = request.input();
        if (input.contains("提示された証拠")) return new LlmResponse("……それが何か？私には関係ありません。昨夜は自宅にいました。", "nervous");
        return new LlmResponse("昨夜のことですか……23時頃は自宅にいました。ほかに何か？", "guarded");
    }
}
