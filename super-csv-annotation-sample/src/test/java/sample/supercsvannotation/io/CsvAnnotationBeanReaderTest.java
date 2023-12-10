package sample.supercsvannotation.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.supercsv.prefs.CsvPreference;

import com.github.mygreen.supercsv.annotation.DefaultGroup;
import com.github.mygreen.supercsv.io.CsvAnnotationBeanReader;

import sample.supercsvannotation.model.SampleNormalBean;

public class CsvAnnotationBeanReaderTest {

	/**
	 * 正常系のテスト
	 * @throws IOException 
	 */
	@Test
	public void testRead_normal() throws IOException {

		File file = new File("src/test/data/test_read_normal.csv");

		try(CsvAnnotationBeanReader<SampleNormalBean> csvReader = new CsvAnnotationBeanReader<>(
				SampleNormalBean.class,
				new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")),
				CsvPreference.STANDARD_PREFERENCE,
				DefaultGroup.class, SampleNormalBean.ReadGroup.class)){

			// エラーメッセージのカスタマイズ
			// csvReader.setExceptionConverter(exceptionConverter);
	
			List<SampleNormalBean> list = new ArrayList<>();
	
	//		final String[] expectedHeaders = new String[] {
	//				"id",
	//				"数字1", "number2",
	//				"string1", "string2",
	//				"date1", "date2",
	//				"enum1", "列挙型2",
	//				"boolean1", "boolean2"
	//		};
	
			// Bean情報のヘッダー情報
			// @CsvColumnのlabelがあればその情報、なければフィールド名
			final String[] definitionHeaders = csvReader.getDefinedHeader();
			System.out.println(Arrays.toString(definitionHeaders));
			
			
			// CSVのヘッダー情報
			final String[] csvHeaders = csvReader.getHeader(true);
			System.out.println(Arrays.toString(csvHeaders));
			
			
			SampleNormalBean bean;
			while ((bean = csvReader.read()) != null) {
				list.add(bean);
				
				System.out.println(bean);
	
			}
		}
	}

}
