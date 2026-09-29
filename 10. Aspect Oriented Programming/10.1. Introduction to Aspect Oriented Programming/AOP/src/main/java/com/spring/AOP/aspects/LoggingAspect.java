package com.spring.AOP.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
	
	@Before("execution(* com.spring.AOP.services.ShipmentService.*(..))")
	public void beforeShipmentServiceMethods(JoinPoint joinPoint) {
		log.info("Before Method Call: {}", joinPoint.getSignature());
	}
}