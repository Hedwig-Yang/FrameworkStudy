package com.atguigu.aspectj.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:KUN
 * @Data:2021/4/20 16:51
 * @Description: 测试AspectJ框架实现AOP
 * @Version:1.0
 */
public class Main {
    /*
     * @description:为什么日志器(logger)要声明为static final类型
     * 1、ogger的构造方法参数是Class，决定了Logger是根据类的结构来进行区分日志，所以一个类只要一个Logger就可以了，故static
     * 2、final表示一种编程习惯，表示该类的Logger只是记录该类的信息，否则日志会无法提供可以令人信服的记录
     */
    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-aspectJ-annotation.xml");
        ArithmeticCaculator ac = ctx.getBean("arithmeticCaculatorImpl", ArithmeticCaculator.class);
        //实际这里获取到的ac是动态代理对象,所以必须用接口对象来接收
        logger.info("ac: "+ac.getClass().getName());
        int result = ac.add(1, 1);
        System.out.println("Main Result : "+ result);

        //异常情况5/0时，后置通知照常运行
        //ac.dvi(5,0);
    }
}


