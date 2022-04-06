package ru.itis.ruzavin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class CountAspect {

	private final static Map<String,Integer> amountOfCalls = new HashMap<>();

//	@Pointcut("execution(* ru.itis.ruzavin.controllers.*.*(..))")
//    public void logAmountMethodsExecution() {
//
//    }

	@Pointcut("@annotation(CountCallsOfMethod)")
	public void logAmountMethodsExecution() {

	}

	@Around("logAmountMethodsExecution()")
	public void logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		String methodName = methodSignature.getName();

		Integer amountBefore = amountOfCalls.get(methodName);

		if (amountBefore == null) {
			amountOfCalls.put(methodName, 1);
		} else {
			amountOfCalls.put(methodName, amountBefore + 1);
		}

		Integer amountAfter = amountOfCalls.get(methodName);

		log.info("method with signature {} calls {} times",methodName, amountAfter);
	}
}
