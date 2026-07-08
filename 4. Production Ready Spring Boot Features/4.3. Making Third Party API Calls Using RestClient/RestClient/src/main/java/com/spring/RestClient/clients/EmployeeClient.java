package com.spring.RestClient.clients;

import java.util.List;

import com.spring.RestClient.dto.EmployeeDTO;

public interface EmployeeClient {
	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
	EmployeeDTO getEmployeeById(Integer id);
	List<EmployeeDTO> getAllEmployees();
} 
