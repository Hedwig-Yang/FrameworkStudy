package com.atguigu.aspectj.annotation;

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
 * @Description: 日志切面
 * @Version:1.0
 */

@Component  //标识为以一个组件，被扫描到，并接收IOC容器管理
@Aspect     //标识为一个切面，表明这个类的身份
public class LoggingAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    /**
     * 前置通知：在目标方法（连接点）执行之前执行。通过切入点表达式找到目标方法
     * 切入点表达式标准写法：execution(访问修饰符 返回值类型 全限定类名.方法名(参数列表))
     */
    @Before("execution(public int com.atguigu.aspectj.annotation.ArithmeticCaculatorImpl.add(int,int))")
    public void beforeMethod(JoinPoint joinPoint){
        //先获取方法签名，再获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取方法参数
        Object[] args = joinPoint.getArgs();
        logger.info("LoggingAspect==> The method "+methodName+" begin with "+ Arrays.asList(args));
    }


    /**
     * 后置通知：在目标方法执行之后执行，不管目标方法是否抛出异常，后置方法都会执行,但不能获取方法结果
     * 切入点表达式通配写法：* com.atguigu.aspectj.annotation.*.*(..))
     * 第一个 * ：任意修饰符 任意返回值
     * 第二个 * ：该包名下的任意类
     *   ..    ：任意参数列表
     * 连接点对象：JoinPoint （我理解为目标对象中被代理的方法）
     */
    @After("ValidationAspect.declarePointCut()")
    public void afterMethod(JoinPoint joinPoint){
        //先获取方法签名，再获取方法名
        String methodName = joinPoint.getSignature().getName();
        logger.info("LoggingAspect==> The method "+methodName+" ends . ");
    }


    /**
     * 返回通知：在目标方法正常执行结束后执行，可以获取方法返回值。
     * 获取方法的返回值：通过returning来指定一个名字，必须要与方法的一个形参名一致。
     */
    @AfterReturning(value = "ValidationAspect.declarePointCut()",returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        //先获取方法签名，再获取方法名
        String methodName = joinPoint.getSignature().getName();
        logger.info("LoggingAspect==> The method "+methodName+" end with : "+result);
    }


    /**
     * 异常通知：在目标抛出异常后执行
     * 获取方法的异常：通过returning来指定一个名字，必须要与方法的一个形参名一致。
     * 可以通过形参中异常的类型来设置抛出指定异常才执行异常通知
     */
    @AfterThrowing(value = "execution(* com.atguigu.aspectj.annotation.*.*(..))",throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint,ArithmeticException ex){
        //先获取方法签名，再获取方法名
        String methodName = joinPoint.getSignature().getName();
        logger.info("LoggingAspect==> The method "+methodName+" occurs Exception : "+ex);
    }


    /**
     * @description:环绕通知：环绕着目标方法执行，可以理解是前置、后置、返回、异常通知的结合体，更像是动态代理的整个过程
     * @param pjp: ProceedingJoinPoint是JointPoint的子类，功能更加强大。
     * @return: java.lang.Object 在环绕通知中需要调用目标方法，因此一定有返回值。
     * 环绕通知因功能重合，不与以上方法同时使用,所以先注释掉注解
     */
    //@Around(value = "execution(* com.atguigu.aspectj.annotation.*.*(..))")
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

