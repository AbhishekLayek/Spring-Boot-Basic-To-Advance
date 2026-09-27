package com.spring.SpringMVC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.SpringMVC.entities.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{

}
