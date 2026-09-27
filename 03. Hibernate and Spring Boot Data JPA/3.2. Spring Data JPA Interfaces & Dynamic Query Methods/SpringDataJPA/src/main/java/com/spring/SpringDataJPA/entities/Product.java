package com.spring.SpringDataJPA.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
		name = "products",
		uniqueConstraints = {
				@UniqueConstraint(name = "sku_unique", columnNames = {"sku"}),
				@UniqueConstraint(name="title_price_unique", columnNames = {"title", "price"})
		},
		indexes = {
				@Index(name = "sku_index", columnList = "sku")
		}
)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 15)
	private String sku;
	
	@Column(nullable = false, length = 30)
	private String title;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	private Integer quantity;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
