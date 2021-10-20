package com.atguigu.boot.config;


import ch.qos.logback.core.db.DBHelper;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.*;


/**
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods：代理bean的方法
 *      Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 *      Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 *      组件依赖必须使用Full模式默认。其他默认是否Lite模式,从而避免IOC容器检查示例，提高启动速度。
 *
 * 4、@Import({User.class, DBHelper.class})
 *      给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
 *
 *
 * 5、@ImportResource("classpath:beans.xml")导入Spring的配置文件，
 *
 */

//@ConditionalOnBean(name = "tom") //条件注解标注在类上时，表示仅当IOC容器存在命名为tom的实例时，才会注册类内部方法代表的Bean对象
@Import({User.class, DBHelper.class}) //向IOC容器导入指定类型的组件
@ImportResource("classpath:beans.xml")
@Configuration(proxyBeanMethods = true) //告诉SpringBoot这是一个配置类 == xml配置文件,proxyBeanMethods默认为true
public class MyConfig {

    @Bean("tom") //可在@Bean标签中自定义容器中配置的实例名称
    public Pet tomcatPet(){
        return new Pet("tomcat");
    }


    /**
     * Full:外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
     */
    @ConditionalOnBean(name = "tom") //仅在容器中存在tom实例的情况下注册User实例，注意：要求tom实例必须在user01实例之前注册
    @Bean //给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user01(){
        User zhangsan = new User("zhangsan", 18);
        //User组件依赖了Pet组件
        zhangsan.setPet(tomcatPet());
        return zhangsan;
    }


}