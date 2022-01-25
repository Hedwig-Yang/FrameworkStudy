package com.atguigu.boot.config;


import ch.qos.logback.core.db.DBHelper;
import com.atguigu.boot.bean.Car;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.web.filter.CharacterEncodingFilter;


/*
   2.1、组件添加
      ● @Configuration
        ○ 配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
        ○ 配置类本身也是组件
        ○ proxyBeanMethods：是否由IOC容器代理bean的方法
            Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
            Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
            组件依赖必须使用Full模式默认。其他默认是否Lite模式,从而避免IOC容器检查示例，提高启动速度。
      ● @Bean、@Component、@Controller、@Service、@Repository
      ● @Import({User.class, DBHelper.class})
          给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
      ● @ComponentScan(com.atguigu.boot) : 标注在主程序类上，用于自定义包扫描路径
      ● @Conditional : 条件装配：满足Conditional指定的条件，则进行组件注入
   2.2、原生配置文件引入
      ● @ImportResource("classpath:beans.xml")导入Spring的配置文件，
   2.3、配置绑定
      ● @ConfigurationProperties 将类与核心配置文件中配置的属性绑定
        ○ @EnableConfigurationProperties标注在IOC容器类 + @ConfigurationProperties标注在组件类
            用于导入第三方类时，类上没有标注@Component时，可以使用
            @EnableConfigurationProperties开启配置绑定，并注册组件到IOC容器。
        ○ @Component + @ConfigurationProperties (都标注在组件类)
 */

//条件注解标注在类上时，表示仅当IOC容器存在命名为tom的实例时，才会注册类内部方法代表的Bean对象
//@ConditionalOnBean(name = "tom")

//向IOC容器导入指定类型的组件，默认组件的名字就是全类名
@Import({User.class, DBHelper.class})

//导入Spring的xml配置文件，方便老旧的xml配置文件在新项目中继续生效
@ImportResource("classpath:beans.xml")

//告诉SpringBoot这是一个配置类 == xml配置文件,proxyBeanMethods默认为true
@Configuration(proxyBeanMethods = true)

/*@EnableConfigurationProperties（开启Car属性配置绑定功能，并自动注册到容器） + @ConfigurationProperties（Car配置绑定）
    = @Component（把这个Car这个组件自动注册到容器中） + @ConfigurationProperties（Car配置绑定）*/
@EnableConfigurationProperties(Car.class)
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

    /**
     * 自定义组件配置方法一：
     *      使用自定义配置的CharacterEncodingFilter，替换底层的组件
     */
//    @Bean
//    public CharacterEncodingFilter filter(){
//        return new CharacterEncodingFilter();
//    }


}