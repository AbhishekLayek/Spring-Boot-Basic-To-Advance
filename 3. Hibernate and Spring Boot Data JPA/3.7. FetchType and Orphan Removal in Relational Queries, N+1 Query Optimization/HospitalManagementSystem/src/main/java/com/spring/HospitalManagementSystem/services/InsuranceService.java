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
	
	@Transactional
	public void addPatientInsurance(Long patientId, Insurance insurance) {
		Patient patient = patientRepository.findById(patientId).orElseThrow();
		
		patient.setInsurance(insurance);
		insurance.setPatient(patient);
		
		System.out.println("Insurance Added For Patient With Id: " + patientId);
	}
	
	public void removePatientInsurance(Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow();
		patient.setInsurance(null);
		
		System.out.println("Insurance Removed For Patient With Id: " + patientId);
	}
}
