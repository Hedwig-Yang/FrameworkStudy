package com.atguigu.boot.bean;


import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 只有在容器中的组件，才会拥有SpringBoot提供的强大功能
 *
 * 将配置文件中的配置参数与JavaBean绑定并注入到IOC容器：
 *     方法一 ： Bean上标注 @ConfigurationProperties(prefix = "mycar") Car属性绑定配置
 *              + @Component 注入到IOC容器
 *     方法二 ： Bean上标注 @ConfigurationProperties(prefix = "mycar") Car属性绑定配置
 *              + @EnableConfigurationProperties（开启Car属性配置绑定功能，并自动注册到容器）
 */

//@Component

//将此类与核心配置文件中名为“mycar”的属性绑定
@ConfigurationProperties(prefix = "mycar")

//使用lombok简化JavaBean的开发
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Car {

    private String brand;
    private Integer price;

    /*public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }*/
}

/**
 * 总结：将配置文件的部分配置绑定到组件中
 *  1、在需要配置的组件类上加注解：
 *          @ConfigurationProperties(prefix = "mycar")（将配置文件中的配置项与需要配置的组件的类绑定）
 *          @Component（将类注册到容器生成组件）
 *  2、在组件类上使用@ConfigurationProperties绑定配置文件的配置项，
 *     在配置类(MyConfig.java)使用@EnableConfigurationProperties(Car.class)，开启配置绑定功能，并注册到IOC容器，生成组件
 */


/*
 4、开发小技巧
    4.1、Lombok 简化JavaBean开发
        1、过程：
            引入lombok依赖 --> 安装lombok插件 --> 使用lombok注解 --> 编译时自动生成代码
        2、常用注解：
            ● @Data ：注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
            ● @NoArgsConstructor ：注在类上，提供类的无参构造
            ● @AllArgsConstructor ：注在类上，提供类的全参构造
            ● @Setter ：注在属性上，提供 set 方法
            ● @Getter ：注在属性上，提供 get 方法
            ● @ToString ：生成toString()方法
            ● @EqualsAndHashCode ：生成equals(Object other) 和 hashCode()方法
            ● @Log4j/@Slf4j ：注在类上，提供对应的 Logger 对象，变量名为 log
        3、注意问题：
            当使用@Data注解时，则有了@EqualsAndHashCode注解，那么就会在此类中存在equals(Object other)
            和 hashCode()方法，且不会使用父类的属性，这就导致了可能的问题。比如，有多个类有相同的部分属性，
            把它们定义到父类中，恰好id（数据库主键）也在父类中，那么就会存在部分对象在比较时，它们并不相等，
            却因为lombok自动生成的equals(Object other) 和 hashCode()方法判定为相等，从而导致出错。
        4、解决方法：
            ● 使用@Getter @Setter @ToString代替@Data并且自定义equals(Object other) 和 hashCode()方法，
              比如有些类只需要判断主键id是否相等即足矣。
            ● 或者使用在使用@Data时同时加上@EqualsAndHashCode(callSuper=true)注解。
    4.2、dev-tools 使用开发者工具实现热更新
        1、过程：
            引入dev-tools依赖 --> 修改代码时Ctrl + F9重新编译，重新加载，相当于自动重启
    4.3、Spring Initailizr（项目初始化向导）
        1、快速创建SpringBoot应用
            ● 自动创建SpringBoot开发目录
            ● 自动引入依赖
            ● 自动创建主程序类
 */

