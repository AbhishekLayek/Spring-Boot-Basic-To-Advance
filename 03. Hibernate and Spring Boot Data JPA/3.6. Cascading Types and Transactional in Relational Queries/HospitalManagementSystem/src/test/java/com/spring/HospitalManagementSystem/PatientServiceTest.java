package com.spring.HospitalManagementSystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.spring.HospitalManagementSystem.services.PatientService;

@SpringBootTest
public class PatientServiceTest {
	
	@Autowired
	private PatientService patientService;
	
	@Test
	void deletePatientTest() {
		patientService.deletePatient(1L);
	}
}
