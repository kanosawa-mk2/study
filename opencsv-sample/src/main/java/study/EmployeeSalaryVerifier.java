package study;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;

public class EmployeeSalaryVerifier implements BeanVerifier<Employee> {

	@Override
	public boolean verifyBean(Employee bean) throws CsvConstraintViolationException {
//		// check salary
		System.out.println("verify:" + bean);
		
		if (bean.getLastSalary() != null && bean.getFirstSalary().compareTo(bean.getLastSalary()) > 0) {
			throw new CsvConstraintViolationException("値の大小比較例外 3項目目と4項目目");
		}
		
//		if(StringUtils.isEmpty(bean.getFirstName()) &&  bean.getFirstSalary() == null && StringUtils.isEmpty(bean.getLastName() ) && bean.getLastSalary()== null) {
//			return false;
//		}
//
		return true;
	}
}
