package com.spring.SecurityExceptionHandling.clients;

import java.util.List;

import com.spring.SecurityExceptionHandling.dto.EmployeeDTO;

public interface EmployeeRestClient {
	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
	
	EmployeeDTO getEmployeeById(Integer id);
	
	List<EmployeeDTO> getAllEmployees();
}
