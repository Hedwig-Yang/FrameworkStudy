package com.atguigu.aspectj.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author:KUN
 * @Data:2021/4/21 15:56
 * @Description: 验证切面(比较两个切面的优先级)
 * @Version:1.0
 */

@Component
@Aspect
@Order(1)  //设置切面优先级，默认值：2147483647，数值越小优先级越高
public class ValidationAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * 声明并重用切入点表达式
     */
    @Pointcut("execution(* com.atguigu.aspectj.annotation.*.*(..))")
    public void declarePointCut(){}


    @Before("declarePointCut()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("ValidationAspect==> The method "+methodName+" begin with "+ Arrays.asList(args));
    }
}
