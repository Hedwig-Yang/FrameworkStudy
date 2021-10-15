package com.atguigu.boot.bean;


import org.springframework.stereotype.Component;


/**
 * 只有在容器中的组件，才会拥有SpringBoot提供的强大功能
 */
@Component
public class Car {

    private String brand;
    private Integer price;


}
