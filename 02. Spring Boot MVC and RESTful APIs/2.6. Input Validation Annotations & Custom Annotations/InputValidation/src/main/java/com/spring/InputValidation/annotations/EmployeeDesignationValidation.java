package com.spring.InputValidation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmployeeDesignationValidator.class})
public @interface EmployeeDesignationValidation {
	String message() default "Employee Designation Should Be Manager or Developer or Tester";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
