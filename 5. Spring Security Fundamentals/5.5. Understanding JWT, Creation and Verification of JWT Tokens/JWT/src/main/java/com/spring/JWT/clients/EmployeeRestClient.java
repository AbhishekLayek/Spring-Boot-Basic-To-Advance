package com.spring.JWT.clients;

import java.util.List;
import com.spring.JWT.dto.EmployeeDTO;

public interface EmployeeRestClient {
	
	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
	
	EmployeeDTO getEmployeeById(Integer id);
	
	List<EmployeeDTO> getAllEmployees();
}
