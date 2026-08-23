package com.spring.TestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.TestApp.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Employee findByEmail(String email);
}
