package com.jicyu.ureddit.common.logging.rpc;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class RpcLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(RpcLoggingAspect.class);

    @Pointcut("execution(* com.jicyu.ureddit.*.provider.rpc.*.*(..))")
    public void loggingPointcut() {
        // 点切表达式，匹配包及其子包下的所有方法
    }

    @Before("loggingPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("++Rpc Before method: {} Request: {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "loggingPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("--Rpc After  method: {} Respond: {}",
                joinPoint.getSignature().getName(),
                result);
    }
}