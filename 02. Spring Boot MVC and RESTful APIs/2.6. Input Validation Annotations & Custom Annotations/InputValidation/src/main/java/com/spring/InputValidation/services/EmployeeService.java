package com.spring.InputValidation.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.spring.InputValidation.configs.ModelMapperConfig;
import com.spring.InputValidation.dto.EmployeeDTO;
import com.spring.InputValidation.entities.EmployeeEntity;
import com.spring.InputValidation.repositories.EmployeeRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeService {
	
	private final EmployeeRepository empRepository;
	private final ModelMapperConfig modelMapper;
	
	public EmployeeService(EmployeeRepository empRepository, ModelMapperConfig modelMapper) {
		this.empRepository = empRepository;
		this.modelMapper = modelMapper;
	}
	
	public boolean isEmployeeExistsById(Integer id) {
		return empRepository.existsById(id);
	}
	
	public Optional<EmployeeDTO> getEmployeeById(Integer id) {
		return empRepository.findById(id).map(empEntity -> modelMapper.map(empEntity, EmployeeDTO.class));
	}

	public List<EmployeeDTO> getAllEmployee() {
		return empRepository.findAll().stream().map(empEntity -> modelMapper.map(empEntity, EmployeeDTO.class)).collect(Collectors.toList());
	}

	public EmployeeDTO createEmployee(EmployeeDTO empDTO) {
		EmployeeEntity empEntity = empRepository.save(modelMapper.map(empDTO, EmployeeEntity.class));
		return modelMapper.map(empEntity, EmployeeDTO.class);
	}

	public EmployeeDTO updateEmploye(Integer id, @Valid EmployeeDTO empDTO) {
		if(!isEmployeeExistsById(id)) return null;
		EmployeeEntity empEntity = modelMapper.map(empDTO, EmployeeEntity.class);
		empEntity.setId(id);
		EmployeeEntity updatedEmpEntity = empRepository.save(empEntity);
		return modelMapper.map(updatedEmpEntity, EmployeeDTO.class);
	}

	public Boolean deleteEmployeeById(Integer id) {
		if(!isEmployeeExistsById(id)) return null;
		empRepository.deleteById(id);
		return true;
	}

	public EmployeeDTO updateEmployeePartially(Integer id, Map<String, Object> updates) {
		if(!isEmployeeExistsById(id)) return null;
		EmployeeEntity empEntity = empRepository.findById(id).get();
		updates.forEach((field, value)->{
			Field fieldToBeUpdate = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
			fieldToBeUpdate.setAccessible(true);
			ReflectionUtils.setField(fieldToBeUpdate, empEntity, value);
		});
		EmployeeEntity updatedEmpEntity = empRepository.save(empEntity);
		return modelMapper.map(updatedEmpEntity, EmployeeDTO.class);
	}
	
	
}	
