package com.spring.APIResponse.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeRoleValidator.class})
public @interface EmployeeRoleValidation {
	String message() default "Role Of Employee Must Be Manager or Developer Or Tester";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
