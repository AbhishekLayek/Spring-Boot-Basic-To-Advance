package com.spring.HospitalManagementSystem.services;

import org.springframework.stereotype.Service;
import com.spring.HospitalManagementSystem.entities.Insurance;
import com.spring.HospitalManagementSystem.entities.Patient;
import com.spring.HospitalManagementSystem.repositories.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceService {
	private final PatientRepository patientRepository;
	
	/*
	 * CascadeType.ALL:
	 * When we perform any operation (save, update, delete) on Patient,
	 * the same operation will automatically apply to Insurance.
	 * This avoids manually saving Insurance separately and keeps both entities in sync.
	 *
	 * @Transactional:
	 * Ensures the entire operation runs in a single transaction.
	 * Either both Patient and Insurance changes are saved together,
	 * or if any error occurs, everything is rolled back.
	 * This maintains data consistency between related entities.
	 */
	
	@Transactional
	public void addPatientInsurance(Long patientId, Insurance insurance) {
		Patient patient = patientRepository.findById(patientId).orElseThrow();
		
		patient.setInsurance(insurance);
		
		insurance.setPatient(patient);
		
		System.out.println("Insurance Added For Patient With Id: " + patientId);
	}
}
