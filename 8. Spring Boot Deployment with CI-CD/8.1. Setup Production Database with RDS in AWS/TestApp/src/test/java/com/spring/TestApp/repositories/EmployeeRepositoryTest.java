package com.spring.TestApp.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;

import com.spring.TestApp.configs.TestContainerConfig;
import com.spring.TestApp.entities.Employee;

@DataJpaTest
@Import(TestContainerConfig.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee employee;
	
	@BeforeEach
	void setup() {
		employee = Employee
				.builder()
				.name("Abhishek")
				.email("abhisheklayek57@gmail.com")
				.age(25)
				.designation("Java Developer")
				.salary(28000.28)
				.build();
	}

	@Test
	void testFindByEmail_whenEmailExist_thenReturnEmployee() {
		// Arrange
		employeeRepository.save(employee);
		
		// Act
		Employee emp = employeeRepository.findByEmail(employee.getEmail());
		
		// Assert
		assertThat(emp).isNotNull();
		assertThat(emp.getEmail()).isEqualTo(employee.getEmail());
	}
	
	@Test
	void testFindByEmail_whenEmailNotExist_thenReturnNull() {
		// Arrange
		String email = "emailnotfound404@gmail.com";
		
		// Act
		Employee emp = employeeRepository.findByEmail(email);
				
		// Assert
		assertThat(emp).isNull();
	}
}