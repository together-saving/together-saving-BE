package com.savle.togethersaving.config;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Aspect
@Component
public class LogAspect {

	@Pointcut("execution(* com.savle.togethersaving..*.*(..))")
	public void allMethodCut() {}

	@Before("allMethodCut()")
	public void beforeMethodLog(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		log.info("======== METHOD CALL : {} ========", signature.toShortString());
	}
}
