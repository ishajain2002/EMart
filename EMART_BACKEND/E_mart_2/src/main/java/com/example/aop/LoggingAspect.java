package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // This pointcut targets all methods in the service layer
    @Pointcut("execution(* com.example.services.*.*(..))")
    public void serviceMethods() {}

    // Log before method execution
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("🔷 Entering method: " + joinPoint.getSignature());
    }

    // Log after method execution (success or exception)
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("🔶 Exiting method: " + joinPoint.getSignature());
    }
}
