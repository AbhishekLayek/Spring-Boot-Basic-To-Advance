package com.spring.TestApp.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;

import com.spring.TestApp.configs.TestContainerConfig;
import com.spring.TestApp.dto.EmployeeDTO;
import com.spring.TestApp.entities.Employee;
import com.spring.TestApp.repositories.EmployeeRepository;

@Import(TestContainerConfig.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Spy
	private ModelMapper modelMapper;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	private Employee mockEmployee;
	private EmployeeDTO mockEmployeeDTO;
	
	@BeforeEach
	void setUp() {
		mockEmployee = Employee
				.builder()
				.id(1L)
				.name("Abhishek")
				.email("abhisheklayek57@gmail.com")
				.age(25)
				.designation("Java Developer")
				.salary(28000.28)
				.build();
		
		mockEmployeeDTO = modelMapper.map(mockEmployee, EmployeeDTO.class);
	}

	@Test
	void testGetEmployeeById_whenEmployeeIdExist_thenReturnEmployeeDTO() {
		// Arrange
		Long id = 1L;
		when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));
		
		// Act
		EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
		
		// Assert
		assertThat(employeeDTO.getId()).isEqualTo(mockEmployee.getId());
		assertThat(employeeDTO.getEmail()).isEqualTo(mockEmployee.getEmail());
		
		verify(employeeRepository, atLeastOnce()).findById(id);
	}
	
	@Test
	void testCreateEmployee_whenEmployeeIsValid_thenSaveEmployeeAndReturnEmployeeDTO() {
		// Arrange
		when(employeeRepository.findByEmail(mockEmployeeDTO.getEmail())).thenReturn(null);
		when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);
		
		// Act
		EmployeeDTO employeeDTO = employeeService.createEmployee(mockEmployeeDTO);
		
		// Assert
		assertThat(employeeDTO.getId()).isEqualTo(mockEmployee.getId());
		assertThat(employeeDTO.getEmail()).isEqualTo(mockEmployee.getEmail());
		
		verify(employeeRepository).save(any(Employee.class));
		
		ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
		
		verify(employeeRepository).save(employeeArgumentCaptor.capture());
		
		Employee capturedEmployee = employeeArgumentCaptor.getValue();
		
		assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());
		
	}

}