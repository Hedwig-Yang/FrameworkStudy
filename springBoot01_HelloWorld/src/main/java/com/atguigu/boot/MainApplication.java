package com.atguigu.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author:Z
 * @Data:2021/10/14 18:14
 * @Description: 主程序类
 * @Version:1.0
 */


/**
 * 主程序类：所有启动的入口
 * @SpringBootApplication:声明这是个SpringBoot应用
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args){
        //1、返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2、查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        System.out.println("IOC容器管理对象如下：");
        for (String name:names){
            System.out.println(name);
        }
    }
}
