package com.spring.HospitalManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.HospitalManagementSystem.entities.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
