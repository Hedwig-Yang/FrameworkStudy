package com.atguigu.boot;

import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import com.atguigu.boot.config.MyConfig;
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

        //3、从容器中获取实例（也叫获取组件）
        Pet tom01 = run.getBean("tom",Pet.class);
        Pet tom02 = run.getBean("tom",Pet.class);
        System.out.println("测试配置类在IOC容器注册的组件是单实例："+(tom01 == tom02));

        //4、配置类本身也是组件，以MyConfig为例
        MyConfig myConfigBean = run.getBean(MyConfig.class);
        System.out.println(myConfigBean);

        //5、判断配置类MyConfig示例对象，调用的组件注册的方法，返回的对象是new出来的还是IOC容器的？
        User user1 = myConfigBean.user01();
        User user2 = myConfigBean.user01();
        /*返回结果为true，证明配置类方法返回单实例对象，来自IOC容器，原因：
         * @Configuration标签默认proxyBeanMethods = true，因此IOC容器返回的配置类示例对象为
         * 代理对象：com.atguigu.boot.config.MyConfig$$EnhancerBySpringCGLIB$$12e7a77c@503fbbc6，
         * 代理对象调用代理方法时：SpringBoot总会检查这个组件是否在IOC容器中，从而保持组件单实例。
         * 如配置proxyBeanMethods = false，返回MyConfig示例对象为：com.atguigu.boot.config.MyConfig@3003697
         * 此时不是代理类对象，调用方法返回的user对象也不是来自于IOC容器，而是new出来的2个不同对象
         */
        System.out.println("配置类返回组件对象："+(user1 == user2));

        //6、proxyBeanMethods = true为全量模式，作用是解决组件依赖的问题，
        //   保证容器中相互以来的组件，都来自于IOC容器以User组件依赖Pet组件为例
        User user01 = run.getBean("user01", User.class);
        Pet tom = run.getBean("tom", Pet.class);
        System.out.println("user内的宠物是否来自于IOC容器："+(user01.getPet() == tom));


    }
}
