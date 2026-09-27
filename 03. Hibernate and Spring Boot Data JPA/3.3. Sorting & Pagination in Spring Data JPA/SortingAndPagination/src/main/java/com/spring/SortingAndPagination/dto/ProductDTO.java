package com.spring.SortingAndPagination.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long id;
	private String sku;
	private String title;
	private BigDecimal price;
	private Integer quantity;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
