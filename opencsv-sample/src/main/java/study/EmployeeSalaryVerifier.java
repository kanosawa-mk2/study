package study;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;

public class EmployeeSalaryVerifier implements BeanVerifier<Employee> {
	
	private static Validator validator;
	
	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	
	

	@Override
	public boolean verifyBean(Employee bean) throws CsvConstraintViolationException {
//		// check salary
		System.out.println("verify:" + bean);
		
//		if (bean.getLastSalary() != null && bean.getFirstSalary().compareTo(bean.getLastSalary()) > 0) {
//			throw new CsvConstraintViolationException("値の大小比較例外 3項目目と4項目目");
//		}
		
		Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(bean);
		
		if (constraintViolations.size() > 0) {
			
			throw new CustomCsvException(constraintViolations);
		}
		
//		if(StringUtils.isEmpty(bean.getFirstName()) &&  bean.getFirstSalary() == null && StringUtils.isEmpty(bean.getLastName() ) && bean.getLastSalary()== null) {
//			return false;
//		}
//
		return true;
	}
}
