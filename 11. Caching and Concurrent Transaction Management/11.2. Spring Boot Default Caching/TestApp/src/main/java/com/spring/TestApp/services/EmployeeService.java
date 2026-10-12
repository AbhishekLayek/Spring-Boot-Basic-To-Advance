package com.spring.TestApp.services;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.TestApp.dto.EmployeeDTO;
import com.spring.TestApp.entities.Employee;
import com.spring.TestApp.exceptions.ResourceNotFoundException;
import com.spring.TestApp.repositories.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;
	private final String CACHE_NAME = "employees";
	
	private void validateEmployeeExistance(Long id) {
		if(! employeeRepository.existsById(id)) throw new ResourceNotFoundException("Employee Not Found With Id: " + id);
	}
	
	@CachePut(cacheNames = CACHE_NAME, key = "#result.id")
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		if(employeeRepository.findByEmail(employeeDTO.getEmail()) != null) throw new RuntimeException("Employee Already Exists With Email: " + employeeDTO.getEmail());
		
		Employee employee = employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
		return modelMapper.map(employee, EmployeeDTO.class);
	}
	
	@Cacheable(cacheNames = CACHE_NAME, key = "#id")
	public EmployeeDTO getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found With Id: " + id));
		
		return modelMapper.map(employee, EmployeeDTO.class);
	}
	
	@CachePut(cacheNames = CACHE_NAME, key = "#result.id")
	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
		validateEmployeeExistance(id);
		Employee employee = modelMapper.map(employeeDTO, Employee.class);
		employee.setId(id);
		return modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);
	}
	
	@CacheEvict(cacheNames = CACHE_NAME, key = "#id")
	public boolean deleteEmployeeById(Long id) {
		validateEmployeeExistance(id);
		employeeRepository.deleteById(id);
		return true;
	}
}