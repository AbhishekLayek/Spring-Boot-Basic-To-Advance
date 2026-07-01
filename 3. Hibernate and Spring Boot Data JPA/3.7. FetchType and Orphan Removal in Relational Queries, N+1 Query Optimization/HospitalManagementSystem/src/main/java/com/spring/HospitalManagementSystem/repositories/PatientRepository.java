package com.spring.HospitalManagementSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.spring.HospitalManagementSystem.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	@Query("SELECT p from Patient p LEFT JOIN FETCH p.appointments") // N+1 Query Optimization
	List<Patient> getAllPatientsWithAppointments();
}
