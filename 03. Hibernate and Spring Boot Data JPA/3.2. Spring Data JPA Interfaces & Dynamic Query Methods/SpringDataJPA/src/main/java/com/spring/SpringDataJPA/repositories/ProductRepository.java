package com.spring.SpringDataJPA.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.SpringDataJPA.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	// Custom Query Methods
	
	Optional<Product> findBySku(String sku);
	
	List<Product> findFirst3By();

	Optional<Product> findByTitleAndPrice(String title, BigDecimal price);
	
	// Custom Query
	
	@Query("SELECT p FROM Product p WHERE p.updatedAt BETWEEN :start AND :end")
	List<Product> findProductUpdatedBetween(LocalDateTime start, LocalDateTime end);
}
