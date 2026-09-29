package com.spring.AOP.services;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShipmentService {
	public String orderPacakge(Long orderId) {
		try {
			log.info("Processing the order ...");
			Thread.sleep(1000);
		}
		catch(InterruptedException e) {
			log.error("Error occurred while processing the order ", e);
		}
		
		return "Order has been processed successfully, Order Id: " + orderId;
	}
	
	public String trackPackage(Long orderId) {
		try {
			log.info("Tracking the order ...");
			Thread.sleep(500);
			throw new RuntimeException("Exception occurred during track packages");
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}