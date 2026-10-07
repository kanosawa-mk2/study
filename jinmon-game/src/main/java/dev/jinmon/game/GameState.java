package dev.jinmon.game;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GameState {
    private int heartRate = 70, tension, suspicion, turns;
    private final Set<String> presentedEvidence = new LinkedHashSet<>();
    private final List<String> conversationHistory = new ArrayList<>();
    private GameResult result = GameResult.ONGOING;

    public int getHeartRate() { return heartRate; }
    public int getTension() { return tension; }
    public int getSuspicion() { return suspicion; }
    public int getTurns() { return turns; }
    public Set<String> getPresentedEvidence() { return Set.copyOf(presentedEvidence); }
    public List<String> getConversationHistory() { return List.copyOf(conversationHistory); }
    public GameResult getResult() { return result; }

    public void recordExchange(String player, String suspect) {
        conversationHistory.add("尋問官: " + player);
        conversationHistory.add("佐藤: " + suspect);
        // Bound prompt history while retaining the latest exchanges.
        while (conversationHistory.size() > 20) conversationHistory.remove(0);
    }
    public void recordQuestion(boolean contradiction) {
        turns++;
        tension = clamp(tension + (contradiction ? 4 : 1), 0, 100);
        heartRate = clamp(heartRate + (contradiction ? 5 : 1), 50, 180);
        if (contradiction) suspicion = clamp(suspicion + 5, 0, 100);
        evaluate();
    }
    public boolean presentEvidence(Evidence evidence) {
        if (!presentedEvidence.add(evidence.id())) return false;
        turns++;
        tension = clamp(tension + evidence.strength(), 0, 100);
        suspicion = clamp(suspicion + evidence.strength(), 0, 100);
        heartRate = clamp(heartRate + evidence.strength(), 50, 180);
        evaluate();
        return true;
    }
    public void finish() { if (result == GameResult.ONGOING) result = GameResult.FAILED; }
    private void evaluate() {
        if (tension >= 35 && suspicion >= 25 && !presentedEvidence.isEmpty()) result = GameResult.CLEARED;
        else if (turns >= 12) result = GameResult.FAILED;
    }
    private static int clamp(int n, int min, int max) { return Math.max(min, Math.min(max, n)); }
}
