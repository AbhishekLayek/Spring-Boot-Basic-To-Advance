package com.spring.HospitalManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.HospitalManagementSystem.entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
