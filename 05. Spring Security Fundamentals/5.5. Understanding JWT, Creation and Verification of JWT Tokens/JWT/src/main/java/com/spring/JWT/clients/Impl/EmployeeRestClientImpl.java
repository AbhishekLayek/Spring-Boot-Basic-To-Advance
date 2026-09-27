package com.spring.JWT.clients.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.spring.JWT.advices.ApiResponse;
import com.spring.JWT.clients.EmployeeRestClient;
import com.spring.JWT.dto.EmployeeDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeRestClientImpl implements EmployeeRestClient{
	private final RestClient restClient;
	
	// Creation Of Logger Object
	Logger log = LoggerFactory.getLogger(EmployeeRestClientImpl.class);

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		
		log.trace("Trying To Create New Employee");
		
		ResponseEntity<ApiResponse<EmployeeDTO>> employeeApiResponseEntity = restClient
				.post()
				.uri("/employees/add")
				.body(employeeDTO)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (req,res)->{
					log.error(new String(res.getBody().readAllBytes()));
					throw new RuntimeException("Employee Not Created");
				})
				.toEntity(new ParameterizedTypeReference<>() {
				});
				
		log.info("Employee Created Successfully");
		log.trace("Created Employee {}", employeeApiResponseEntity.getBody().getData());
		
		return employeeApiResponseEntity.getBody().getData();
	}

	@Override
	public EmployeeDTO getEmployeeById(Integer id) {
		
		log.trace("Trying To Retrieve Employee With Id {}",id);
		
		ResponseEntity<ApiResponse<EmployeeDTO>> employeeApiResponseEntity = restClient
				.get()
				.uri("/employees/find?id={id}",id)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (req,res)->{
					log.error(new String(res.getBody().readAllBytes()));
					throw new RuntimeException("Employee Not Found With Id: " + id);
				})
				.toEntity(new ParameterizedTypeReference<>() {
				});
				
		log.info("Successfully Retrieved Employee With Id {}",id);
		log.trace("Retrieved Employee {}", employeeApiResponseEntity.getBody().getData());
		
		return employeeApiResponseEntity.getBody().getData();
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		
		log.trace("Trying To Retrieve All Employees");
		
		ResponseEntity<ApiResponse<List<EmployeeDTO>>> employeeApiResponseEntity = restClient
				.get()
				.uri("/employees/findAll")
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (req,res)->{
					log.error(new String(res.getBody().readAllBytes()));
					throw new RuntimeException("No Employees Found");
				})
				.toEntity(new ParameterizedTypeReference<>() {
				});
				
		log.info("All Employees Retrieved Successfully");
		log.trace("Retrieved Employees {}", employeeApiResponseEntity.getBody().getData());
		
		return employeeApiResponseEntity.getBody().getData();
	}
}
