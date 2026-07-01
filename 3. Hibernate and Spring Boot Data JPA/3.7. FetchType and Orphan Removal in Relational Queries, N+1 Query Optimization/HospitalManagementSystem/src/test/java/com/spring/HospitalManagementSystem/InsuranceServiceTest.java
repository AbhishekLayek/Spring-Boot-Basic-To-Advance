package com.spring.HospitalManagementSystem;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.HospitalManagementSystem.entities.Insurance;
import com.spring.HospitalManagementSystem.services.InsuranceService;

@SpringBootTest
public class InsuranceServiceTest {
	@Autowired
	private InsuranceService insuranceService;
	
	/*@Test
	void addPatientInsuranceTest() {
		Insurance insurance = Insurance
				.builder()
				.policyNumber("POL1001")
				.provider("HDFC ERGO")
				.validUntil(LocalDate.of(2028, 12, 31))
				.build();
		
		insuranceService.addPatientInsurance(1L, insurance);
	} */
	
	@Test
	void removePatientInsuranceTest() {
		insuranceService.removePatientInsurance(1L);
	}
}
