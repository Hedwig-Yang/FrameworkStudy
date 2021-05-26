package com.telecom.MvcCombineSpring.beans;

/**
 * @Author:KUN
 * @Data:2021/5/26 16:36
 * @Description: Person对象
 * @Version:1.0
 */
public class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello(){
        System.out.println("My Name is"+name);
    }
}
