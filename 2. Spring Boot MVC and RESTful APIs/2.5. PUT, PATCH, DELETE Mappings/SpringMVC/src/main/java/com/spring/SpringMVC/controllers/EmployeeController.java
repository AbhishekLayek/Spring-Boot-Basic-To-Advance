package com.spring.SpringMVC.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.SpringMVC.dto.EmployeeDTO;
import com.spring.SpringMVC.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService empService;
	
	public EmployeeController(EmployeeService empService) {
		this.empService = empService;
	}
	
	@GetMapping("/find")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@RequestParam Integer id){
		Optional<EmployeeDTO> empDTO = empService.getEmployeeById(id);
		return empDTO.map(emp -> ResponseEntity.ok(emp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		return ResponseEntity.ok(empService.getAllEmployees());
	}
	
	@PostMapping("/add")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO empDTO){
		return new ResponseEntity<>(empService.createEmployee(empDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<EmployeeDTO> updateEmployee(@RequestParam Integer id, @RequestBody EmployeeDTO inputEmpDTO){
		EmployeeDTO  outputEmpDTO = empService.updateEmployee(id, inputEmpDTO);
		if(outputEmpDTO == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(outputEmpDTO);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Boolean> deleteEmployeeById(@RequestParam Integer id){
		Boolean status = empService.deleteEmployeeById(id);
		if(status == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(status);
	}
	
	@PatchMapping("/partialUpdate")
	public ResponseEntity<EmployeeDTO> updateEmployeePartially(@RequestParam Integer id, @RequestBody Map<String, Object> updates){
		EmployeeDTO empDTO = empService.updateEmployeePartially(id, updates);
		if(empDTO == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(empDTO);
	}
}
