package com.spring.SpringMVC_PersistenceLayer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SpringMVC_PersistenceLayer.entities.Employee;
import com.spring.SpringMVC_PersistenceLayer.repositories.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeRepository empRepository;
	
	public EmployeeController(EmployeeRepository empRepository) {
		this.empRepository = empRepository;
	}



	@GetMapping("/find")
	public Employee getEmployeeById(@RequestParam int id) {
		return empRepository.findById(id).orElse(null);
	}
	
	@GetMapping("/findAll")
	public List<Employee> getAllEmployees(){
		return empRepository.findAll();
	}
	
	@PostMapping("/add")
	public Employee createEmployee(@RequestBody Employee employee) {
		return empRepository.save(employee);
	}
}
