package com.spring.TestApp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.TestApp.dto.EmployeeDTO;
import com.spring.TestApp.entities.Employee;
import com.spring.TestApp.repositories.EmployeeRepository;

class EmployeeControllerTestIT extends AbstractIntegrationTest{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Employee employee;
	private EmployeeDTO employeeDTO;
	
	@BeforeEach()
	void setUp() {
		employee = Employee
				.builder()
				.name("Abhishek")
				.email("abhisheklayek57@gmail.com")
				.age(25)
				.designation("Java Developer")
				.salary(28000.28)
				.build();
		
		employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		
		employeeRepository.deleteAll();
	}

	@Test
	void testGetEmployeById_Success() {
		Employee savedEmployee = employeeRepository.save(employee);
		employeeDTO.setId(savedEmployee.getId());
		
		webTestClient
			.get()
			.uri("/employees/find?id={id}", savedEmployee.getId())
			.exchange()
			.expectStatus().isOk()
			.expectBody(EmployeeDTO.class)
			.isEqualTo(employeeDTO);
	}
	
	@Test
	void testGetEmployeeById_Failure() {
		webTestClient
			.get()
			.uri("/employee/find?id={id}", 1L)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	@Test
	void testCreateEmployee_whenEmployeeEmailNotExist_thenSaveEmployee() {
		webTestClient
			.post()
			.uri("/employees/add")
			.bodyValue(employeeDTO)
			.exchange()
			.expectStatus().isCreated()
			.expectBody()
			.jsonPath("$.email").isEqualTo(employee.getEmail());
	}
	
	@Test
	void testCreateEmployee_whenEmployeeEmailAlreadyExist_thenThrowException() {
		employeeRepository.save(employee);
		
		webTestClient
		.post()
		.uri("/employees/add")
		.bodyValue(employeeDTO)
		.exchange()
		.expectStatus().is5xxServerError();
	}
	
	@Test
	void testUpdateEmployee_whenEmployeeIdExist_thenUpdateEmployee() {
		Employee savedEmployee = employeeRepository.save(employee);
		
		employeeDTO.setAge(26);
		employeeDTO.setSalary(48000.00);
		
		webTestClient
			.put()
			.uri("/employees/update?id={id}", savedEmployee.getId())
			.bodyValue(employeeDTO)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.age").isEqualTo(employeeDTO.getAge())
			.jsonPath("$.salary").isEqualTo(employeeDTO.getSalary());
	}
	
	@Test
	void testUpdateEmployee_whenEmployeeIdNotExist_thenThrowException() {
		webTestClient
		.put()
		.uri("/employees/update?id={id}", 1L)
		.bodyValue(employeeDTO)
		.exchange()
		.expectStatus().is5xxServerError();
	}
	
	@Test
	void testDeleteEmployeeById_whenEmployeeIdExist_thenDeleteEmployee() {
		Employee savedEmployee = employeeRepository.save(employee);
		
		webTestClient
			.delete()
			.uri("/employees/delete?id={id}", savedEmployee.getId())
			.exchange()
			.expectStatus().isOk();
	}
	
	@Test
	void testDeleteEmployeeById_whenEmployeeNotIdExist_thenDeleteEmployee() {
		
		webTestClient
			.delete()
			.uri("/employees/delete?id={id}", 1L)
			.exchange()
			.expectStatus().is5xxServerError();
	}
}
