package com.spring.HospitalManagementSystem.services;

import org.springframework.stereotype.Service;

import com.spring.HospitalManagementSystem.repositories.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {
	private final PatientRepository patientRepository;
	
	
	public void deletePatient(Long patientId) {
		patientRepository.findById(patientId).orElseThrow();
		patientRepository.deleteById(patientId);
		System.out.println("Patient Deleted With Id: " + patientId);
	}
}
