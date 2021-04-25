package com.atguigu.aspectj.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author:KUN
 * @Data:2021/4/20 16:18
 * @Description: 使用xml方式配置日志切面
 * @Version:1.0
 */


public class LoggingAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    public void beforeMethod(JoinPoint joinPoint){
        //先获取方法签名，再获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取方法参数
        Object[] args = joinPoint.getArgs();
        logger.info("LoggingAspect==> The method "+methodName+" begin with "+ Arrays.asList(args));
    }


    public void afterMethod(JoinPoint joinPoint){
        //先获取方法签名，再获取方法名
        String methodName = joinPoint.getSignature().getName();
        logger.info("LoggingAspect==> The method "+methodName+" ends . ");
    }


    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        //先获取方法签名，再获取方法名
        String methodName = joinPoint.getSignature().getName();
        logger.info("LoggingAspect==> The method "+methodName+" end with : "+result);
    }


    public void afterThrowingMethod(JoinPoint joinPoint,ArithmeticException ex){
        //先获取方法签名，再获取方法名
        String methodName = joinPoint.getSignature().getName();
        logger.info("LoggingAspect==> The method "+methodName+" occurs Exception : "+ex);
    }


    public Object aroundMethod(ProceedingJoinPoint pjp){
        //前置通知
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        logger.info("LoggingAspect Around==> The method "+methodName+" begin with "+ Arrays.asList(args));
        try {
            //执行目标方法
            Object result = pjp.proceed();
            //返回通知
            return result;
        } catch (Throwable e) {
            //异常通知
            e.printStackTrace();
        }finally {
            //后置通知
            logger.info("LoggingAspect Around==> The method "+methodName+" ends . ");
        }
        return null;
    }



}

