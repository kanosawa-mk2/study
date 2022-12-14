package study;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class Main {

	public static void main(String[] args) throws IllegalStateException, FileNotFoundException {

		final CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(new FileReader("test\\test.csv"))
				.withType(Employee.class)
				.withVerifier(new EmployeeSalaryVerifier()) // カスタム検証を行う
				.withThrowExceptions(true) // true:すぐにExceptionをスローする
				.withIgnoreEmptyLine(true) // true:空行を無視する
				//.withIgnoreLeadingWhiteSpace(true) // true の場合、フィールド内の引用符の前の空白は無視されます。
				//				.withQuoteChar('\"')
				.build();
		
		try {
			// csvToBean.iteratorで１行目のカスタム検証が実行されるので注意
			Iterator<Employee> it = csvToBean.iterator();
			while (it.hasNext()) {

				Employee employee = (Employee) it.next(); // 2行目以降のカスタム検証が実行される
				System.out.println(employee);

			}
		} catch (Exception e) {
			boolean csvError = false;
			Throwable e1 = e.getCause();
			while (e1 != null) {
				if (e1 instanceof CustomCsvException) {
					// カスタム検証エラーの場合
					CustomCsvException csvEx = (CustomCsvException) e1;
					System.err
							.println(MessageFormat.format("{0}:エラー発生行 {1}", e1.getMessage(), csvEx.getLineNumber()));

					showErrorDetails(csvEx.getConstraintViolations());
					csvError = true;
					break;
				}else if( e1 instanceof CsvException) {
					// opencsvの検証エラーの場合
					CsvException csvEx = (CsvException) e1;
					System.err.println(MessageFormat.format("{0}:エラー発生行 {1}", e1.getMessage(), csvEx.getLineNumber()));
					csvError = true;
					break;
				}
				e1 = e1.getCause();
			}
			if(!csvError) {
				e.printStackTrace();
			}

		}

//		List<CsvException> exceptions = csvToBean.getCapturedExceptions();
//
//		System.out.println(MessageFormat.format("Number of Mistakes: {0}", Objects.toString(exceptions.size())));
//		csvToBean.getCapturedExceptions().stream().forEach(
//				ex -> System.out
//						.println(MessageFormat.format("Line {0}: {1}", ex.getLineNumber(), ex.getMessage(), ex)));

	}
	
	private static <T> void showErrorDetails(
            Set<ConstraintViolation<T>> constraintViolations) {
        for (ConstraintViolation<T> violation : constraintViolations) {
            System.out.println("----------");
            System.out.println("MessageTemplate : " + violation.getMessageTemplate());
            System.out.println("Message : " + violation.getMessage());
            System.out.println("InvalidValue : " + violation.getInvalidValue());
            System.out.println("PropertyPath : " + violation.getPropertyPath());
            System.out.println("RootBeanClass : " + violation.getRootBeanClass());
            System.out.println("RootBean : " + violation.getRootBean());
        }
    }

}
