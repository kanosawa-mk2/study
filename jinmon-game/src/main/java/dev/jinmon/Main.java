package dev.jinmon;

import dev.jinmon.game.*;
import dev.jinmon.llm.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String key = System.getenv("OPENAI_API_KEY");
        LlmClient client = key == null || key.isBlank() ? new DemoLlmClient()
                : new OpenAiResponsesClient(key, System.getenv("OPENAI_MODEL"));
        if (key == null || key.isBlank()) System.out.println("[デモモード] OPENAI_API_KEY未設定のため固定応答で実行します。\n");
        InterrogationGame game = new InterrogationGame(client);
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================================\n        AI尋問ゲーム\n====================================\n\n事件：\n昨夜23時頃、殺人事件が発生しました。\n\n容疑者：\n佐藤 35歳 会社員\n\n------------------------------------\n容疑者：\n「……私は何も知りません。」\n------------------------------------");
        while (game.state().getResult() == GameResult.ONGOING) {
            System.out.println("\nあなたの行動を入力してください。\n1. 質問する\n2. 証拠を提示する\n3. 現在の状態を見る\n4. ゲームを終了する\n>");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> { System.out.println("質問：\n>"); String q = scanner.nextLine(); print(game.ask(q)); }
                    case "2" -> {
                        for (int i = 0; i < game.evidence().size(); i++) System.out.println((i + 1) + ". " + game.evidence().get(i).title());
                        System.out.println(">"); int n = Integer.parseInt(scanner.nextLine().trim());
                        if (n < 1 || n > game.evidence().size()) { System.out.println("番号が正しくありません。"); break; }
                        print(game.presentEvidence(n - 1));
                    }
                    case "3" -> System.out.printf("心拍数: %d / 緊張度: %d / 疑惑度: %d / ターン: %d / 提示済み証拠: %d%n", game.state().getHeartRate(), game.state().getTension(), game.state().getSuspicion(), game.state().getTurns(), game.state().getPresentedEvidence().size());
                    case "4" -> game.state().finish();
                    default -> System.out.println("1〜4を入力してください。");
                }
            } catch (Exception e) { System.out.println("処理に失敗しました: " + e.getMessage()); }
        }
        System.out.println(game.state().getResult() == GameResult.CLEARED ? "\n容疑者：\n「……もう無理だ。私がやりました。」\nゲームクリア！" : "\n尋問終了。今回は自白を引き出せませんでした。");
    }
    private static void print(LlmResponse response) { System.out.println("\n容疑者：\n「" + response.dialogue() + "」"); }
}
