package com.atguigu.boot.bean;


import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 只有在容器中的组件，才会拥有SpringBoot提供的强大功能
 */
//@Component
@ConfigurationProperties(prefix = "mycar")
//使用lombok简化JavaBean的开发
@Data
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


