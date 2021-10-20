package com.atguigu.boot;

import ch.qos.logback.core.db.DBHelper;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import com.atguigu.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:Z
 * @Data:2021/10/14 18:14
 * @Description: 主程序类，也叫主配置类
 * @Version:1.0
 */


/**
 * 主程序类：所有启动的入口
 * @SpringBootApplication:声明这是个SpringBoot应用
 */
@SpringBootApplication  // =@SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan(com.atguigu.boot)
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

        //7、MyConfig配置类中@Import({User.class, DBHelper.class})标签，作用是调用指定类的无参构造方法，在IOC容器中创建组件实例
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        System.out.println("所有user组件的名称如下：");
        for(String userName:beanNamesForType){
            System.out.println(userName);
        }
        DBHelper bean = run.getBean(DBHelper.class);
        System.out.println("DBhelper的组件："+bean);

        //8、学习条件注解@Conditional的使用，以@ConditionalOnBean为例，
        boolean containTom = run.containsBean("tom");
        System.out.println("IOC容器中tom实例存在："+ containTom);
        boolean containsUser01 = run.containsBean("user01");
        System.out.println("IOC容器中user01实例存在："+ containsUser01);

        //9、学习组件导入注解：@ImportResource,适用于需要导入指定的xml配置文件的情况
        boolean haha = run.containsBean("haha");
        boolean hehe = run.containsBean("hehe");
        System.out.println("存在haha:"+haha);
        System.out.println("存在hehe:"+hehe);
    }
}
