package com.spring.RestClient.clients.Impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.spring.RestClient.advices.ApiResponse;
import com.spring.RestClient.clients.EmployeeClient;
import com.spring.RestClient.dto.EmployeeDTO;
import com.spring.RestClient.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient{
	private final RestClient restClient;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiREsponse = restClient
				.post()
				.uri("employees/add")
				.body(employeeDTO)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (req,res)->{
					throw new ResourceNotFoundException("Employee Cannot Created");
				})
				.toEntity(new ParameterizedTypeReference<>() {
				});
		
		return employeeDTOApiREsponse.getBody().getData();
	}

	@Override
	public EmployeeDTO getEmployeeById(Integer id) {
		ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiREsponse = restClient
				.get()
				.uri("employees/find?id={id}", id)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (req,res)->{
					throw new ResourceNotFoundException("Employee Not Found With Id: " + id);
				})
				.toEntity(new ParameterizedTypeReference<>() {
				});
		
		return employeeDTOApiREsponse.getBody().getData();
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		ResponseEntity<ApiResponse<List<EmployeeDTO>>> employeeDTOApiREsponse = restClient
				.get()
				.uri("employees/findAll")
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (req,res)->{
					throw new ResourceNotFoundException("No Employees Found");
				})
				.toEntity(new ParameterizedTypeReference<>() {
				});
		
		return employeeDTOApiREsponse.getBody().getData();
	}
}
