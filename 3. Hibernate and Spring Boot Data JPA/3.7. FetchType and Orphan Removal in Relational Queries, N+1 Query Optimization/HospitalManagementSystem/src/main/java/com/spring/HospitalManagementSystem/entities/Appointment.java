package com.spring.HospitalManagementSystem.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime appointmentTime;
	
	@Column(nullable = false, length = 500)
	private String reason;
	
	@Column(nullable = false, length = 15)
	private String status;
	
	@ManyToOne
	private Patient patient; // Owning Side, Child Side
	
	@ManyToOne
	private Doctor doctor; // Owning Side, Child Side
}
