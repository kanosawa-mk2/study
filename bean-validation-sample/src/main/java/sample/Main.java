package sample;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Main {

	public static void main(String[] args) {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Employee employee = new Employee();
		
		employee.setLastName("１２３４５６７８９０１");
		
		Set<ConstraintViolation<Employee>> constraintViolations = validator
                .validate(employee);

        // 4. check result
        int errorCount = constraintViolations.size();
        System.out.println("validate error count : " + errorCount);
        if (errorCount > 0) {
            showErrorDetails(constraintViolations);
        }
		
		

	}
	
	private static <T> void showErrorDetails(
            Set<ConstraintViolation<T>> constraintViolations) {
        for (ConstraintViolation<T> violation : constraintViolations) {
            System.out.println("----------");
            System.out.println(
                    "MessageTemplate : " + violation.getMessageTemplate());
            System.out.println("Message : " + violation.getMessage());
            System.out.println("InvalidValue : " + violation.getInvalidValue());
            System.out.println("PropertyPath : " + violation.getPropertyPath());
            System.out.println("RootBeanClass : " + violation.getRootBeanClass());
            System.out.println("RootBean : " + violation.getRootBean());
        }
    }

}
