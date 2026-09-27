package com.spring.SignupLogin.clients;

import java.util.List;

import com.spring.SignupLogin.dto.EmployeeDTO;

public interface EmployeeRestClient {
	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
	
	EmployeeDTO getEmployeeById(Integer id);
	
	List<EmployeeDTO> getAllEmployees();
}
