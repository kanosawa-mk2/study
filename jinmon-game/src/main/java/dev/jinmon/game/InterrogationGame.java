package dev.jinmon.game;

import dev.jinmon.llm.LlmClient;
import dev.jinmon.llm.LlmRequest;
import dev.jinmon.llm.LlmResponse;
import dev.jinmon.prompt.SuspectPromptBuilder;
import java.util.List;

public class InterrogationGame {
    private final Suspect suspect = new Suspect("佐藤", 35, "会社員", "昨夜23時頃は自宅にいた", "昨夜23時頃、事件現場にいた");
    private final List<Evidence> evidence = List.of(
            new Evidence("camera", "防犯カメラ映像", "22:50頃、事件現場付近に容疑者によく似た人物が映っている。", 8),
            new Evidence("location", "スマートフォン位置情報", "23:00頃、容疑者のスマートフォンが事件現場付近にあった。", 10),
            new Evidence("store", "コンビニの利用記録", "22:40頃、事件現場付近のコンビニで容疑者のカードが使われた。", 7));
    private final GameState state = new GameState();
    private final LlmClient llm;
    private final SuspectPromptBuilder prompts = new SuspectPromptBuilder();
    public InterrogationGame(LlmClient llm) { this.llm = llm; }
    public Suspect suspect() { return suspect; }
    public GameState state() { return state; }
    public List<Evidence> evidence() { return evidence; }
    public LlmResponse ask(String question) {
        boolean contradiction = question.contains("本当に") || question.contains("矛盾") || question.contains("嘘");
        state.recordQuestion(contradiction);
        LlmResponse answer = llm.generate(new LlmRequest(prompts.instructions(suspect), prompts.input(question, state, evidence)));
        state.recordExchange(question, answer.dialogue());
        return answer;
    }
    public LlmResponse presentEvidence(int index) {
        Evidence item = evidence.get(index);
        if (!state.presentEvidence(item)) return new LlmResponse("その証拠はすでに提示されています。", "guarded");
        String text = "提示された証拠: " + item.title() + " — " + item.description();
        LlmResponse answer = llm.generate(new LlmRequest(prompts.instructions(suspect), prompts.input(text, state, evidence)));
        state.recordExchange(text, answer.dialogue());
        return answer;
    }
}
