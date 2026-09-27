package com.spring.TestApp.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
	private Long id;
	
	@NotBlank(message = "Name Cannot Be Blank")
	@Size(min = 3, max = 40, message = "Name Must Be Within 3-20 Characters")
	private String name;
	
	@NotBlank(message = "Email Cannot Be Blank")
	@Email(message = "Please Provide Valid Email")
	@Size(min = 5, max = 100, message = "Email Must Be Within 3-20 Characters")
	private String email;
	
	@Min(value = 18, message = "Minimum Age Is 18")
	@Max(value = 80, message = "Maximum Age Is 80")
	private Integer age;
	
	@NotBlank(message = "Designation Cannot Be Blank")
	private String designation;
	
	@Digits(integer = 6, fraction = 2, message = "Maximum Salary Will Be Of 6 Digits")
	private Double salary;
}
