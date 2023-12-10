package sample.supercsvannotation.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.github.mygreen.supercsv.annotation.CsvBean;
import com.github.mygreen.supercsv.annotation.CsvColumn;
import com.github.mygreen.supercsv.annotation.constraint.CsvLengthMax;
import com.github.mygreen.supercsv.annotation.constraint.CsvNumberMax;
import com.github.mygreen.supercsv.annotation.constraint.CsvRequire;
import com.github.mygreen.supercsv.annotation.conversion.CsvDefaultValue;
import com.github.mygreen.supercsv.annotation.format.CsvBooleanFormat;
import com.github.mygreen.supercsv.annotation.format.CsvDateTimeFormat;
import com.github.mygreen.supercsv.annotation.format.CsvEnumFormat;
import com.github.mygreen.supercsv.annotation.format.CsvNumberFormat;

import lombok.Data;

@CsvBean(header=true, validateHeader=true)
@Data
public class SampleNormalBean implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 読み込み時のグループ */
	public static interface ReadGroup {
	}

	/** 書き込み時のグループ */
	public static interface WriteGroup {
	}

	@CsvColumn(number = 1)
	@CsvRequire
	private int id;

	@CsvColumn(number = 2, label = "数字1")
	@CsvNumberFormat(pattern = "###,###,###")
	@CsvRequire
	@CsvNumberMax(value = "999,999")
	private int number1;

	@CsvColumn(number = 3)
	private Double number2;

	@CsvColumn(number = 4)
	@CsvRequire
	private String string1;

	@CsvColumn(number = 5)
	@CsvDefaultValue("")
	@CsvLengthMax(value = 6)
	private String string2;

	@CsvColumn(number = 6)
	@CsvRequire
	private Date date1;

	@CsvColumn(number = 7)
	@CsvDateTimeFormat(pattern = "yyyy年MM月dd日")
	private Timestamp date2;

	@CsvColumn(number = 8)
	@CsvEnumFormat(ignoreCase = true)
	@CsvDefaultValue("BLUE")
	private SampleEnum enum1;

	@CsvColumn(number = 9, label = "列挙型2")
	@CsvEnumFormat(ignoreCase = true, selector = "aliasName")
	@CsvDefaultValue(value = "青", groups = ReadGroup.class)
	private SampleEnum enum2;

	@CsvColumn(number = 10)
	@CsvRequire
	private boolean boolean1;

	@CsvColumn(number = 11)
	@CsvBooleanFormat(readForTrue = { "○" }, readForFalse = { "×" }, writeAsTrue = "○", writeAsFalse = "×")
	@CsvDefaultValue(value = "○", groups = ReadGroup.class)
	@CsvDefaultValue(value = "×", groups = WriteGroup.class)
	private Boolean boolean2;
}
