package com.spring.ExceptionHandling.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.ExceptionHandling.annotations.EmployeeRoleValidation;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	private Integer id;
	
	@NotBlank(message = "Name Cannot Be Blank")
	@Size(min = 3, max = 20, message = "Name Should Be Within 3-20 Characters")
	private String name;
	
	@NotBlank(message = "Email Cannot Be Blank")
	@Email(message = "Please Enter Valid Email")
	private String email;
	
	@Min(value = 18, message = "Minimum Age Should Be 18")
	@Max(value = 80, message = "Maximum Age Should Be 80")
	private Integer age;
	
	@NotBlank(message = "Designation Cannot Be Blank")
	@EmployeeRoleValidation
	private String designation;
	
	@PastOrPresent(message = "Date Of Joining Must Be Less Than Or Equal To Present Date")
	private LocalDate dateOfJoining;
	
	@Digits(integer = 6, fraction = 2, message = "Maximum Salary Can Be Of 6 Digits")
	private Double salary;
	
	@JsonProperty(defaultValue = "isActive")
	@AssertTrue(message = "Employees Should Be Active")
	private Boolean isActive;
}
