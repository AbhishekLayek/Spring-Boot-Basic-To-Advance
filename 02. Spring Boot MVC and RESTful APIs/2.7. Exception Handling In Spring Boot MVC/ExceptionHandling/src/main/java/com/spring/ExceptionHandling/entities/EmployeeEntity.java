package com.spring.ExceptionHandling.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private Integer age;
	private LocalDate dateOfJoining;
	private Double salary;
	@JsonProperty(defaultValue = "isActive")
	private Boolean isActive;
}
