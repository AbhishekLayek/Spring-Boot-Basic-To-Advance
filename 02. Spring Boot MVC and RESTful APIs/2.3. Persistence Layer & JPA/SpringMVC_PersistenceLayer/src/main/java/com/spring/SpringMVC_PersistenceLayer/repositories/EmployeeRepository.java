package com.spring.SpringMVC_PersistenceLayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.SpringMVC_PersistenceLayer.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
}
