package com.example.adorsys.profiling;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogExecutionTimeImpl {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    @Around("execution (@com.example.adorsys.profiling.LogExecutionTime * *.*(..))")
    public Object timedMethod(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        LogExecutionTime timed = method.getAnnotation(LogExecutionTime.class);

        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis() - start;

        if (end > timed.limit()) {
            logger.error("Error! Execution time className = {}, methodName = {}, threadId = {}, time = {} mSec too long!",
                    pjp.getSignature().getDeclaringTypeName(),
                    ((MethodSignature) pjp.getSignature()).getMethod().getName(),
                    System.currentTimeMillis() - start,
                    Thread.currentThread().getId());
        }
        return result;
    }

}
