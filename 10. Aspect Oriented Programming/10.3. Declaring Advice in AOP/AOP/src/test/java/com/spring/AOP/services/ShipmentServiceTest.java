package com.spring.AOP.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShipmentServiceTest {
	
	@Autowired
	private ShipmentService shipmentService;

	@Test
	void testOrderPacakge() {
		shipmentService.orderPacakge(101L);
	}
	
	@Test
	void testTrackPacakge() {
		shipmentService.trackPackage(102L);
	}
}