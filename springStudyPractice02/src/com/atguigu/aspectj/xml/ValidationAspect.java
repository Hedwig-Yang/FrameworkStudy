package com.atguigu.aspectj.xml;

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

public class ValidationAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("ValidationAspect==> The method "+methodName+" begin with "+ Arrays.asList(args));
    }
}
