package com.spring.HospitalManagementSystem;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.HospitalManagementSystem.entities.Appointment;
import com.spring.HospitalManagementSystem.services.AppointmentService;

@SpringBootTest
public class AppointmentServiceTest {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Test
	void createAppointmentTest() {
		Appointment appointment = Appointment
				.builder()
				.appointmentTime(LocalDateTime.of(2026, 7, 10, 7, 30))
				.reason("Chest Pain")
				.status("Scheduled")
				.build();
		
		System.out.println(appointmentService.addAppointment(1L, 1L, appointment));
	}
}
