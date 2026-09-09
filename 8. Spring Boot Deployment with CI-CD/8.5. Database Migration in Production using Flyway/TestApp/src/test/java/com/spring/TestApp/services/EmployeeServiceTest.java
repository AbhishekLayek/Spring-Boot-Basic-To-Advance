package com.spring.TestApp.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
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
import com.spring.TestApp.exceptions.ResourceNotFoundException;
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
	void testGetEmployeeById_whenEmployeeIdNotExist_thenThrowResourceNotFoundException() {
		// Arrange
		when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		// Act & Assert
		assertThatThrownBy(()-> employeeService.getEmployeeById(1L))
			.isInstanceOf(ResourceNotFoundException.class)
			.hasMessage("Employee Not Found With Id: 1");
		
		verify(employeeRepository, atLeastOnce()).findById(anyLong());
		
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
	
	@Test
	void testCreateEmployee_whenTryingToSaveEmployeeWithExistingEmail_thenReturnRuntimeException() {
		// Arrange
		when(employeeRepository.findByEmail(mockEmployeeDTO.getEmail())).thenReturn(mockEmployee);
		
		// Act & Assert
		assertThatThrownBy(()-> employeeService.createEmployee(mockEmployeeDTO))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("Employee Already Exists With Email: " + mockEmployeeDTO.getEmail());
		
		verify(employeeRepository).findByEmail(mockEmployeeDTO.getEmail());
		verify(employeeRepository, never()).save(any());
		
	}
	
	@Test
	void testUpdateEmployee_whenEmployeeIdExist_thenUpdateEmployeeAndReturnUpdatedEmployeeDTO() {
		// Arrange
		when(employeeRepository.existsById(mockEmployeeDTO.getId())).thenReturn(true);
		mockEmployeeDTO.setSalary(35000.00);
		
		Employee updatedEmployee = modelMapper.map(mockEmployeeDTO, Employee.class);
		
		when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

		
		// Act
		EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(mockEmployeeDTO.getId(), mockEmployeeDTO);
		
		// Assert
		assertThat(updatedEmployeeDTO).usingRecursiveComparison().isEqualTo(mockEmployeeDTO);
		
		verify(employeeRepository).existsById(mockEmployeeDTO.getId());
		verify(employeeRepository).save(any(Employee.class));
	}
	
	@Test
	void testUpdateEmployee_whenEmployeeIdNotExist_thenThrowResourceNotFoundException() {
		// Arrange
		when(employeeRepository.existsById(anyLong())).thenReturn(false);
		
		// Act & Assert
		assertThatThrownBy(()-> employeeService.updateEmployee(1L, mockEmployeeDTO))
			.isInstanceOf(ResourceNotFoundException.class)
			.hasMessage("Employee Not Found With Id: 1");
		
		verify(employeeRepository, only()).existsById(anyLong());
		verify(employeeRepository, never()).save(any(Employee.class));
	}
	
	@Test
	void testDeleteEmployeeById_whenEmployeeIdExist_thenDeleteEmployeeAndReturnTrue() {
		// Arrange
		when(employeeRepository.existsById(anyLong())).thenReturn(true);
		
		// Act
		Boolean result = employeeService.deleteEmployeeById(anyLong());
		
		// Assert
		assertTrue(result);
		verify(employeeRepository).existsById(anyLong());
		verify(employeeRepository).deleteById(anyLong());
	}
	
	@Test
	void testDeleteEmployeeById_whenEmployeeIdNotExist_thenThrowResourceNotFoundException() {
		// Arrange
		when(employeeRepository.existsById(anyLong())).thenReturn(false);
		
		// Act & Assert
		assertThatThrownBy(()-> employeeService.deleteEmployeeById(1L))
			.isInstanceOf(ResourceNotFoundException.class)
			.hasMessage("Employee Not Found With Id: 1");
		
		verify(employeeRepository).existsById(anyLong());
		verify(employeeRepository, never()).deleteById(anyLong());
	}
}