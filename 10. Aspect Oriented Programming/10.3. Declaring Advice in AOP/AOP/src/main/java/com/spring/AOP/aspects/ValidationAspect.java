package com.spring.AOP.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ValidationAspect {
	
	@Pointcut("execution(* com.spring.AOP.services.*.*(..))")
	public void logAllServiceMethodPointCut() {
	}
	
	@Around("logAllServiceMethodPointCut()")
	public Object validateOrderId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object args[] = proceedingJoinPoint.getArgs();
		
		Long orderId = (Long)args[0];
		
		if(orderId > 0) return proceedingJoinPoint.proceed();
		
		return new RuntimeException("Cannot Call With Negative OrderId");
	}
}