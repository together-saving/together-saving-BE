package com.savle.togethersaving.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Aspect
@Component
public class LogAspect {

	@Pointcut("execution(* com.savle.togethersaving..*.*(..))")
	public void allMethodCut() {}

	@Pointcut("execution(* com.savle.togethersaving.controller..*.*(..))")
	public void allControllerCut() {}

	@Before("allControllerCut()")
	public void beforeControllerMethodLog(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
			.currentRequestAttributes()).getRequest();
		log.info("-------- REQUEST URI : {} --------", request.getRequestURI());
		logArgsArray(joinPoint.getArgs());
	}

	@Before(value = "allMethodCut()")
	public void beforeRepositoryMethodLog(JoinPoint joinPoint) {
		log.info("======== SIGNATURE CALLED : {} ========", joinPoint.getSignature().toShortString());
		logArgsArray(joinPoint.getArgs());
	}

	@Around("allMethodCut()")
	public Object aroundMethodLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		StopWatch watch = new StopWatch();
		watch.start();
		Object result = proceedingJoinPoint.proceed();
		watch.stop();
		String signature = proceedingJoinPoint.getSignature().toShortString();
		log.info("======== {} TIME SPENT : {} seconds! ========", signature, watch.getTotalTimeSeconds());
		return result;
	}

	private void logArgsArray(Object[] args) {
		if (args.length == 0) {
			return;
		}
		log.info("======== ARGUMENTS ========");
		for (Object arg : args) {
			log.info("{} {}", arg.getClass().getSimpleName(), arg);
		}
		log.info("===========================");
	}
}
