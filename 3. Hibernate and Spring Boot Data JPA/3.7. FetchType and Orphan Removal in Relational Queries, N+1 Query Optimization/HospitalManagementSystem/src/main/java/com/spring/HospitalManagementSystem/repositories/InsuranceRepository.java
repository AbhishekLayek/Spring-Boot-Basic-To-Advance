package com.spring.HospitalManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.HospitalManagementSystem.entities.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long>{

}
