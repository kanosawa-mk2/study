package sample;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Employee {

	//@NotNull(message = "NULLだめ")
	@NotNull
	private String firstName;

	@Size(max=10)
	private String lastName;

	private Long firstSalary;

	private Long lastSalary;

}
