package com.telecom.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot02HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot02HelloWorldApplication.class, args);
	}

}

/**
 * 总结：使用Spring Initailizr（项目初始化向导）创建SpringBoot项目
 * 	作用：1、根据选择开发场景，自动依赖引入。
 * 		 2、自动创建项目结构
 * 		 3、自动编写好主配置类XxxApplication类
 */
