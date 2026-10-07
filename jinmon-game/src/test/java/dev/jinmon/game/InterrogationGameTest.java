package dev.jinmon.game;

import dev.jinmon.llm.LlmResponse;
import dev.jinmon.prompt.SuspectPromptBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InterrogationGameTest {
    @Test void stateChangesAreJavaManagedAndClamped() {
        GameState state = new GameState();
        state.recordQuestion(true);
        assertEquals(75, state.getHeartRate());
        assertEquals(4, state.getTension());
        assertEquals(5, state.getSuspicion());
    }
    @Test void evidenceIsRecordedOnlyOnce() {
        GameState state = new GameState();
        Evidence evidence = new Evidence("camera", "カメラ", "映像", 8);
        assertTrue(state.presentEvidence(evidence));
        assertFalse(state.presentEvidence(evidence));
        assertEquals(1, state.getPresentedEvidence().size());
    }
    @Test void confessionRequiresTensionSuspicionAndEvidence() {
        GameState state = new GameState();
        for (int i = 0; i < 8; i++) state.recordQuestion(true);
        assertEquals(GameResult.ONGOING, state.getResult());
        state.presentEvidence(new Evidence("phone", "位置", "現場", 10));
        assertEquals(GameResult.CLEARED, state.getResult());
    }
    @Test void turnLimitEndsGame() {
        GameState state = new GameState();
        for (int i = 0; i < 12; i++) state.recordQuestion(false);
        assertEquals(GameResult.FAILED, state.getResult());
    }
    @Test void historyKeepsRecentTwentyLines() {
        GameState state = new GameState();
        for (int i = 0; i < 15; i++) state.recordExchange("質問" + i, "返答" + i);
        assertEquals(20, state.getConversationHistory().size());
        assertTrue(state.getConversationHistory().get(0).contains("質問5"));
    }
    @Test void promptIncludesRulesStateEvidenceAndHistory() {
        GameState state = new GameState();
        Evidence evidence = new Evidence("cam", "カメラ映像", "人物", 8);
        state.presentEvidence(evidence);
        state.recordExchange("どこにいた？", "自宅です。");
        SuspectPromptBuilder builder = new SuspectPromptBuilder();
        assertTrue(builder.instructions(new Suspect("佐藤", 35, "会社員", "自宅", "現場")).contains("簡単に自白しない"));
        String input = builder.input("なぜ？", state, java.util.List.of(evidence));
        assertTrue(input.contains("カメラ映像"));
        assertTrue(input.contains("自宅です"));
        assertTrue(input.contains("緊張度"));
    }
    @Test void gameCanUseMockWithoutApi() {
        InterrogationGame game = new InterrogationGame(request -> new LlmResponse("自宅にいました", "calm"));
        assertEquals("自宅にいました", game.ask("昨夜どこに？").dialogue());
        assertEquals(2, game.state().getConversationHistory().size());
    }
}
