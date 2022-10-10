package sample;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Maina {

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {

		final CSVFormat.Builder formatBuilder = CSVFormat.RFC4180.builder();
		formatBuilder.setIgnoreEmptyLines(false); // 空行スキップすると行番号がずれるので使用しない
		formatBuilder.setTrim(false); // トリムはしない
		formatBuilder.setNullString(""); // 空文字はnullとする

		final CSVFormat format = formatBuilder.build();

		try (Reader reader = Files.newBufferedReader(Paths.get("test\\\\test.csv"), Charset.forName("UTF-8"));
				CSVParser parser = format.parse(reader);) {
			for (final CSVRecord record : parser) {

				System.out.println("--------------------");
				System.out.println("getRecordNumber:" + record.getRecordNumber());
				if (record.size() == 1 && record.get(0) == null ) {
					System.out.println("空行をスキップ");
					continue;
				}
				
				if (record.size() < 4) {
					System.out.println("カラム数が不正です");
					continue;
				}

				System.out.println("size:" + record.size());
				System.out.println("get(0):" + record.get(0));
				System.out.println("get(1):" + record.get(1));
				System.out.println("get(2):" + record.get(2));
				System.out.println("get(3):" + record.get(3));

				// csvtoBean
				// validation
				// RegistTable

			}
		}

	}

}
