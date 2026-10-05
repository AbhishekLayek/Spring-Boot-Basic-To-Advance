package com.spring.AOP.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
	
	// Execution Kind Pointcut
	@Before("execution(* com.spring.AOP.services.ShipmentService.orderPacakge(..))")
	public void logBeforeOrderPacakgeExecution(JoinPoint joinPoint) {
		log.info("Before Called From LoggingAspect Signature {}", joinPoint.getSignature());
	}
	
	// Within Kind Pointcut
	@Before("within(* com.spring.AOP.services.ShipmentService.*)")
	public void logBeforeShipmentService() {
		log.info("Service Calls");
	}
	
	// Annotation Kind Pointcut
	@Before("@annotation(org.springframework.transaction.annotation.Transactional)")
	public void logTransactionalMethods() {
		log.info("Before Transactional Annotation Calls");
	}
	
	// Pointcut Expression
	@Pointcut("execution(* com.spring.AOP.services.ShipmentService.trackPacakge(..))")
	public void logTrackPacakge() {
	}
	
	@Before("logTrackPacakge()")
	public void logBeforeTrackPacakge(JoinPoint joinPoint) {
		log.info("Before Called From LoggingAspect Signature {}", joinPoint.getSignature());
	}
	
	@After("logTrackPacakge()")
	public void logAfterTrackPacakge(JoinPoint joinPoint) {
		log.info("After Called From LoggingAspect Signature {}", joinPoint.getSignature());
	}
}
