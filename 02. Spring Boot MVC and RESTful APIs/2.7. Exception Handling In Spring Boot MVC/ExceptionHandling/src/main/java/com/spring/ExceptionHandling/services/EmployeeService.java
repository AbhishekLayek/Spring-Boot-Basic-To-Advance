package com.spring.ExceptionHandling.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.spring.ExceptionHandling.configs.ModelMapperConfig;
import com.spring.ExceptionHandling.dto.EmployeeDTO;
import com.spring.ExceptionHandling.entities.EmployeeEntity;
import com.spring.ExceptionHandling.exceptions.ResourceNotFoundException;
import com.spring.ExceptionHandling.repositories.EmployeeRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeService {
	
	private EmployeeRepository empRepository;
	private ModelMapperConfig modelMapper;
	
	public EmployeeService(EmployeeRepository empRepository, ModelMapperConfig modelMapper) {
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
	}
	
	public boolean ValidateEmployeeExistance(Integer id) {
		if(! empRepository.existsById(id)) throw new ResourceNotFoundException("Employee Not Found With Id: " + id);
		return true;
	}

	public Optional<EmployeeDTO> getEmployeeById(Integer id) {
		return empRepository.findById(id).map(empEntity -> modelMapper.map(empEntity, EmployeeDTO.class));
	}

	public List<EmployeeDTO> getAllEmployee() {
		return empRepository.findAll().stream().map(empEntity -> modelMapper.map(empEntity, EmployeeDTO.class)).collect(Collectors.toList());
	}

	public EmployeeDTO createEmployee(@Valid EmployeeDTO empDTO) {
		EmployeeEntity empEntity = empRepository.save(modelMapper.map(empDTO, EmployeeEntity.class));
		return modelMapper.map(empEntity, EmployeeDTO.class);
	}

	public EmployeeDTO updateEmployee(Integer id, @Valid EmployeeDTO empDTO) {
		ValidateEmployeeExistance(id);
		
		EmployeeEntity empEntity = modelMapper.map(empDTO, EmployeeEntity.class);
		empEntity.setId(id);
		return modelMapper.map(empRepository.save(empEntity), EmployeeDTO.class);
	}

	public String deleteEmployeeById(Integer id) {
		ValidateEmployeeExistance(id);
		empRepository.deleteById(id);
		return "Employee Deleted With Id: " + id;
	}

	public EmployeeDTO updateEmployeePartially(Integer id, Map<String, Object> updates) {
		ValidateEmployeeExistance(id);
		EmployeeEntity empEntity = empRepository.findById(id).get();
		updates.forEach((field, value) -> {
			Field fieldToBeUpdate = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
			fieldToBeUpdate.setAccessible(true);
			ReflectionUtils.setField(fieldToBeUpdate, empEntity, value);
		});
		
		return modelMapper.map(empRepository.save(empEntity), EmployeeDTO.class);
	}
	
	
}
