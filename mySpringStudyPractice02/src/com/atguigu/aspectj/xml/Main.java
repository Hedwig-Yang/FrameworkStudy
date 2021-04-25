package com.atguigu.aspectj.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:KUN
 * @Data:2021/4/20 16:51
 * @Description: 测试xml方式配置切面，相比注解配置更稍微复杂点，要求能看懂
 * @Version:1.0
 */
public class Main {
    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("spring-aspectJ-xml.xml");
        ArithmeticCaculator ac = ctx.getBean("arithmeticCaculatorImpl", ArithmeticCaculator.class);
        //实际这里获取到的ac是动态代理对象,所以必须用接口对象来接收
        logger.info("ac: "+ac.getClass().getName());
        int result = ac.add(1, 1);
        System.out.println("Main Result : "+ result);

        //异常情况5/0时，后置通知照常运行
        //ac.dvi(5,0);
    }
}


