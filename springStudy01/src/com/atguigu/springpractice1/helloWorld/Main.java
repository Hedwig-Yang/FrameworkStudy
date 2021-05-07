package com.atguigu.springpractice1.helloWorld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:KUN
 * @Data:2021/4/15 15:11
 * @Description: 测试
 * @Version:1.0
 */
public class Main {
    public static void main(String[] args) {
        //1、创建IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2、获取person对象
        Person person = (Person) ctx.getBean("person");

        //3、调用方法
        person.sayHello();
    }
}
