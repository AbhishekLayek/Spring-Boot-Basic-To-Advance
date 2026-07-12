package com.spring.OpenAPI_Swagger.clients;

import java.util.List;
import com.spring.OpenAPI_Swagger.dto.EmployeeDTO;

public interface EmployeeRestClient {
	
	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
	
	EmployeeDTO getEmployeeById(Integer id);
	
	List<EmployeeDTO> getAllEmployees();
}
