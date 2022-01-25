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


/*
 3、自动配置原理
    3.1、引导加载自动配置类
        1、SpringBootApplication 代表当前是一个配置类
        2、@ComponentScan 指定扫描哪些位置的Spring注解
        3、@EnableAutoConfiguration
           ● @AutoConfigurationPackage 自动配置包？指定了默认的包规则
           ● @Import(AutoConfigurationImportSelector.class) 利用组件中的方法，批量导入默认组件
    3.2、按需开启自动配置项
        1、虽然我们127个场景的所有自动配置启动的时候默认全部加载。xxxxAutoConfiguration
        2、按照条件装配规则（@Conditional），最终会按需配置。
    3.3、修改默认配置
        1、@ConditionalOnMissingBean :
            IOC容器中没有这个用户配置组件时，采用自动配置的组件，否则直接使用用户配置的组件
        2、SpringBoot默认会在底层配好所有的组件。但是如果用户自己配置了以用户的优先
 */



/*
   @SpringBootApplication = @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan(com.atguigu.boot)
     1、@SpringBootConfiguration ：@Configuration代表当前是一个配置类
     2、@EnableAutoConfiguration ：自动配置包
          ● @AutoConfigurationPackage 根据包扫描路径导入（自定义）组件
            ○ @Import(AutoConfigurationPackages.Registrar.class)
              给容器中导入一个Registrar组件，利用Registrar将主程序所在包(默认包扫描情况)下所有组件导入IOC容器
            ○ public @interface AutoConfigurationPackage {.....}
              使用@interface定义一个注释类
          ● @Import(AutoConfigurationImportSelector.class)
            从META-INF/spring.factories位置来加载一个文件,给容器中批量导入一些需要自动加载的（默认）组件
          ● public @interface EnableAutoConfiguration {......}
            使用@interface定义一个注释类
     3、@ComponentScan(com.atguigu.boot) : 定义包扫描路径，不配置的情况下默认在Application类的同文件夹下的所有文件及子文件夹
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args){
        /*
         * 1、返回IOC容器
         */
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        /*
         * 2、查看容器里面的组件
         */
        String[] names = run.getBeanDefinitionNames();
        System.out.println("IOC容器管理对象如下：");
        for (String name:names){
            System.out.println(name);
        }

        /*
         * 3、从容器中获取实例（也叫获取组件）
         */
        Pet tom01 = run.getBean("tom",Pet.class);
        Pet tom02 = run.getBean("tom",Pet.class);
        System.out.println("测试配置类在IOC容器注册的组件是单实例："+(tom01 == tom02));

        /*
         * 4、配置类本身也是组件，以MyConfig为例
         */
        MyConfig myConfigBean = run.getBean(MyConfig.class);
        System.out.println(myConfigBean);

        /*
         * 5、判断配置类MyConfig示例对象，调用的组件注册的方法，返回的对象是new出来的还是IOC容器的？
         */
        User user1 = myConfigBean.user01();
        User user2 = myConfigBean.user01();
        System.out.println("配置类返回组件对象："+(user1 == user2));
        /*返回结果为true，证明配置类方法返回单实例对象，来自IOC容器，原因：
         * @Configuration标签默认proxyBeanMethods = true，因此IOC容器返回的配置类示例对象为
         * 代理对象：com.atguigu.boot.config.MyConfig$$EnhancerBySpringCGLIB$$12e7a77c@503fbbc6，
         * 代理对象调用代理方法时：SpringBoot总会检查这个组件是否在IOC容器中，从而保持组件单实例。
         * 如配置proxyBeanMethods = false，返回MyConfig示例对象为：com.atguigu.boot.config.MyConfig@3003697
         * 此时不是代理类对象，调用方法返回的user对象也不是来自于IOC容器，而是new出来的2个不同对象
         */


        /*
         * 6、proxyBeanMethods = true为全量模式，作用是解决组件依赖的问题，
         *   保证容器中相互依赖的组件，都来自于IOC容器以User组件依赖Pet组件为例
         */
        User user01 = run.getBean("user01", User.class);
        Pet tom = run.getBean("tom", Pet.class);
        System.out.println("user内的宠物是否来自于IOC容器："+(user01.getPet() == tom));

        /*
         * 7、MyConfig配置类中@Import({User.class, DBHelper.class})标签，
         *    作用是调用指定类的无参构造方法，在IOC容器中创建组件实例，默认组件的名字就是全类名。
         */
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        System.out.println("所有user组件的名称如下：");
        for(String userName:beanNamesForType){
            System.out.println(userName);
        }
        DBHelper bean = run.getBean(DBHelper.class);
        System.out.println("DBhelper的组件："+bean);

        /*
         * 8、学习条件注解@Conditional的使用，以@ConditionalOnBean为例，
         *    在某某条件下才在IOC容器中注册组件。
         *    条件注解标注在类上时，表示仅当满足条件时，IOC容器才会注册类内部方法代表的Bean对象
         */
        boolean containTom = run.containsBean("tom");
        System.out.println("IOC容器中tom实例存在："+ containTom);
        boolean containsUser01 = run.containsBean("user01");
        System.out.println("IOC容器中user01实例存在："+ containsUser01);

        /*
         * 9、学习组件导入注解：@ImportResource,适用于需要导入指定的xml配置文件的情况
         */
        boolean haha = run.containsBean("haha");
        boolean hehe = run.containsBean("hehe");
        System.out.println("存在haha:"+haha);
        System.out.println("存在hehe:"+hehe);
    }
}

/*
  组件注册过程总结：
    1、SpringBoot先加载所有的自动配置类  xxxxxAutoConfiguration
    2、每个自动配置类按照条件进行生效，默认都会绑定配置文件指定的值。xxxxProperties类里面拿。xxxProperties类和配置文件进行了绑定
    3、生效的配置类就会给容器中装配很多组件
    4、只要容器中有这些组件，相当于这些功能就有了

  自动配置过程：
    xxxxxAutoConfiguration加载配置类 ---> 按需生成组件组件  --->
    xxxxProperties类里面拿值装配  ---> xxxxProperties类从application.properties配置文件获取

  定制化配置
     ○ 用户直接自己@Bean替换底层的组件
     ○ 用户去看这个组件是获取的配置文件什么值就去修改。

 */
