package study;

import javax.validation.constraints.NotEmpty;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;

@Data
public class Employee {

	@NotEmpty
//	@CsvBindByName(column = "First Name", required = true)
	@CsvBindByPosition(position = 0)
	private String firstName;

//	@CsvBindByName(column = "Last Name", required = true)
	@CsvBindByPosition(position = 1)
	private String lastName;

//	@CsvBindByName(column = "First Salary", required = true)
	@CsvBindByPosition(position = 2)
	private Long firstSalary;

//	@CsvBindByName(column = "Last Salary")
	@CsvBindByPosition(position = 3)
	private Long lastSalary;

}
