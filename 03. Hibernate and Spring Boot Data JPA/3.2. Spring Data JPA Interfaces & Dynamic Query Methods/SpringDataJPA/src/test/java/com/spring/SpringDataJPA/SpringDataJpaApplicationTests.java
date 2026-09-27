package com.spring.SpringDataJPA;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.SpringDataJPA.entities.Product;
import com.spring.SpringDataJPA.repositories.ProductRepository;

@SpringBootTest
class SpringDataJpaApplicationTests {
	
	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}
	
	
	@Test
	void createProduct() {
		Product product = Product
				.builder().sku("SKU1006")
				.title("Laptop")
				.price(BigDecimal.valueOf(80000.00))
				.quantity(10)
				.build();

		System.out.println(productRepository.save(product));
	}

	@Test
	void getAllProduct() {
		List<Product> products = productRepository.findAll();
		products.stream().forEach((product) -> {
			System.out.println(product);
		});
	}

	@Test
	void getProductBySKU() {
		Optional<Product> product = productRepository.findBySku("SKU1004");
		System.out.println(product.get());
	}

	@Test
	void getFirst3Products() {
		List<Product> products = productRepository.findFirst3By();
		products.stream().forEach((product) -> {
			System.out.println(product);
		});
	}

	@Test
	void getProductByTitleAndPrice() {
		Optional<Product> product = productRepository.findByTitleAndPrice("Wireless Mouse", BigDecimal.valueOf(599.99));
		System.out.println(product.get());
	}
}
