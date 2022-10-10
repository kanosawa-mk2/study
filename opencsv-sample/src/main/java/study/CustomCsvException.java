package study;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.opencsv.exceptions.CsvConstraintViolationException;

public class CustomCsvException extends CsvConstraintViolationException  {
	
	
	Set<ConstraintViolation<Employee>> constraintViolations;
	

    public Set<ConstraintViolation<Employee>> getConstraintViolations() {
		return constraintViolations;
	}


	public CustomCsvException(Set<ConstraintViolation<Employee>> constraintViolations) {
		super("CSV検証エラーが発生しました。");
        this.constraintViolations = constraintViolations;
    }
    
    
	
	

}
