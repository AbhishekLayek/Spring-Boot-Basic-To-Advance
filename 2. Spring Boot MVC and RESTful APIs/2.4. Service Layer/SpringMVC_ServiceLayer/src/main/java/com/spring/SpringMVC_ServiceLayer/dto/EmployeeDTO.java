package com.spring.SpringMVC_ServiceLayer.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	private Integer id;
	private String name;
	private String email;
	private Integer age;
	private String designation;
	private LocalDate dateOfJoining;
	private Boolean active;
}
