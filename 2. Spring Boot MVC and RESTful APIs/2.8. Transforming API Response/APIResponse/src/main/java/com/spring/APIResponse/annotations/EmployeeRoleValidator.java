package com.spring.APIResponse.annotations;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String>{

	@Override
	public boolean isValid(String inputRole, ConstraintValidatorContext context) {
		List<String> roles = List.of("Manager", "Developer", "Tester");
		return roles.contains(inputRole);
	}
}
