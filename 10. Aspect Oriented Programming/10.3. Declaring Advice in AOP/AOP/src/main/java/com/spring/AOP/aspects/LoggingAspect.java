package com.spring.AOP.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
	
	@Pointcut("execution(* com.spring.AOP.services.*.*(..))")
	public void logAllServiceMethodPointCut() {
	}
	
	@Before("logAllServiceMethodPointCut()")
	public void logBeforeServiceMethodCall(JoinPoint joinPoint) {
		log.info("Before Service Method Call, {}", joinPoint.getSignature());
	}
	
	@After("logAllServiceMethodPointCut()")
	public void logAfterServiceMethodCall(JoinPoint joinPoint) {
		log.info("After Service Method Call, {}", joinPoint.getSignature());
	}
	
	@AfterReturning(value = "logAllServiceMethodPointCut()", returning = "returnedObj")
	public void logAfterReturnServiceMethodCall(JoinPoint joinPoint, Object returnedObj) {
		log.info("After Returning Service Method Call, {}", joinPoint.getSignature());
		log.info("After Returning Service Method Call, {}", returnedObj);
	}
	
	@AfterThrowing("logAllServiceMethodPointCut()")
	public void logAfterThrowServiceMethodCall(JoinPoint joinPoint) {
		log.info("After Throwing Service Method Call, {}", joinPoint.getSignature());
	}
	
	@Around("logAllServiceMethodPointCut()")
	public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Long startTime = System.currentTimeMillis();
		Object returnedValue = proceedingJoinPoint.proceed();
		Long endTime = System.currentTimeMillis();
		log.info("Time Taken For {} is {}", proceedingJoinPoint.getSignature(), (endTime - startTime));
		return returnedValue;
	}
}