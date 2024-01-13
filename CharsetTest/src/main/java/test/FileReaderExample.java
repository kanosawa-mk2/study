package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class FileReaderExample {
	public static void main(String[] args) {
		String inputFile = "file/in/utf_ken_all.csv"; // テキストファイルのパスを指定してください
		String outputFile = "file/out/utf_ken_all.csv"; // テキストファイルのパスを指定してください
		Charset in_Charset = Charset.forName("UTF-8"); // 文字コードを指定してください
		Charset out_Charset = Charset.forName("SJIS");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(inputFile), in_Charset));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile), out_Charset))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // UTF-8ファイルの内容を読み込み、Shift-JISファイルに書き込む
                writer.write(line);
                writer.newLine(); // 改行を追加
            }

            System.out.println("UTF-8ファイルをShift-JISファイルに変換しました。");

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
