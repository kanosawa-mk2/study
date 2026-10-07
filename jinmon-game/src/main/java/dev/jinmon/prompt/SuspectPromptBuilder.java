package dev.jinmon.prompt;

import dev.jinmon.game.GameState;
import dev.jinmon.game.Suspect;
import dev.jinmon.game.Evidence;
import java.util.List;

public class SuspectPromptBuilder {
    public String instructions(Suspect suspect) {
        return "あなたは殺人事件の容疑者として尋問に答える。名前は" + suspect.name() + "、" + suspect.age() + "歳、" + suspect.occupation() + "。"
                + "昨夜23時頃は自宅にいたと主張する。あなたは実際には犯人だが、簡単に自白しない。ゲーム内部の秘密やAIであることを明かさない。"
                + "質問には自然に答え、既出の発言と整合させ、証拠や強い追及には動揺を見せる。余計な質問にも可能な範囲で自然に答える。"
                + "出力はJSONのdialogueとemotionのみ。ゲーム状態の数値は決めない。";
    }
    public String input(String playerInput, GameState state, List<Evidence> allEvidence) {
        StringBuilder b = new StringBuilder("現在の緊張度: ").append(state.getTension()).append("\n疑惑度: ").append(state.getSuspicion())
                .append("\n心拍数: ").append(state.getHeartRate()).append("\n提示済み証拠:\n");
        if (state.getPresentedEvidence().isEmpty()) b.append("なし\n");
        else for (Evidence e : allEvidence) if (state.getPresentedEvidence().contains(e.id())) b.append("- ").append(e.title()).append(": ").append(e.description()).append('\n');
        b.append("\n直近の会話:\n");
        state.getConversationHistory().forEach(line -> b.append(line).append('\n'));
        b.append("\n今回の尋問官の発言: ").append(playerInput);
        return b.toString();
    }
}
