package com.spring.TestApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.TestApp.dto.EmployeeDTO;
import com.spring.TestApp.services.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
		return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/find")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@RequestParam Long id){
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}
	
	@PutMapping("/update")
	public ResponseEntity<EmployeeDTO> updateEmployee(@RequestParam Long id, @RequestBody EmployeeDTO empDTO){
		return ResponseEntity.ok(employeeService.updateEmployee(id, empDTO));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Boolean> deleteEmployeeById(@RequestParam Long id){
		return ResponseEntity.ok(employeeService.deleteEmployeeById(id));
	}
}
