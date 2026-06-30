package com.spring.HospitalManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.HospitalManagementSystem.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
