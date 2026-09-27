package com.spring.Logging.clients.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.spring.Logging.advices.ApiResponse;
import com.spring.Logging.clients.EmployeeRestClient;
import com.spring.Logging.dto.EmployeeDTO;
import com.spring.Logging.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeRestClientImpl implements EmployeeRestClient{
	
	private final RestClient restClient;
	
	// Create Logger Object
	Logger log = LoggerFactory.getLogger(EmployeeRestClientImpl.class);
	
	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		log.trace("Trying To Create Employee");
		ResponseEntity<ApiResponse<EmployeeDTO>> employeeApiResponseEntity = restClient
				.post()
				.uri("employees/add")
				.body(employeeDTO)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
					log.error(new String(res.getBody().readAllBytes()));
					throw new ResourceNotFoundException("Employee Not Created");
				})
				.toEntity(new ParameterizedTypeReference<>() {
				});
		log.info("Employee Created Successfully");
		log.trace("Created Employee: {}" , employeeApiResponseEntity.getBody().getData());
		return employeeApiResponseEntity.getBody().getData();
	}

	@Override
	public EmployeeDTO getEmployeeById(Integer id) {
		log.trace("Trying To Get Employee With ID {}", id);
		ResponseEntity<ApiResponse<EmployeeDTO>> employeeApiResponseEntity = restClient
				.get()
				.uri("employees/find?id={id}", id)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
					log.error(new String(res.getBody().readAllBytes()));
					throw new ResourceNotFoundException("Employee Not Found With Id:" + id);
				})
				.toEntity(new ParameterizedTypeReference<>() {
				});
		log.info("Employee With Id {} Successfully Retrieved", id);
		log.trace("Retrieved Employee: {}" , employeeApiResponseEntity.getBody().getData());
		return employeeApiResponseEntity.getBody().getData();
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		log.trace("Trying To Get All Employees");
		ResponseEntity<ApiResponse<List<EmployeeDTO>>> employeeApiResponseEntity = restClient
				.get()
				.uri("employees/findAll")
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
					log.error(new String(res.getBody().readAllBytes()));
					throw new ResourceNotFoundException("No Employees Found");
				})
				.toEntity(new ParameterizedTypeReference<>() {
				});
		log.info("All Employees Successfully Retrieved");
		log.trace("Retrieved Employees: {}" , employeeApiResponseEntity.getBody().getData());
		return employeeApiResponseEntity.getBody().getData();
	}
}
