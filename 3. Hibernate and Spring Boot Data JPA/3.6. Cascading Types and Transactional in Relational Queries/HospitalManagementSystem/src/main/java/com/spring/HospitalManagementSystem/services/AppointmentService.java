package com.spring.HospitalManagementSystem.services;

import org.springframework.stereotype.Service;

import com.spring.HospitalManagementSystem.entities.Appointment;
import com.spring.HospitalManagementSystem.entities.Doctor;
import com.spring.HospitalManagementSystem.entities.Patient;
import com.spring.HospitalManagementSystem.repositories.AppointmentRepository;
import com.spring.HospitalManagementSystem.repositories.DoctorRepository;
import com.spring.HospitalManagementSystem.repositories.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {
	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;
	private final AppointmentRepository appointmentRepository;
	
	@Transactional
	public Appointment addAppointment(Long patientId, Long doctorId, Appointment appointment) {
		Patient patient = patientRepository.findById(patientId).orElseThrow();
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
		
		appointment.setPatient(patient);
		appointment.setDoctor(doctor);
		
		return appointmentRepository.save(appointment);
	}
}
