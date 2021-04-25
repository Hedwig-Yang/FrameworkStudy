package com.atguigu.springpractice1.helloWorld;

/**
 * @Author:KUN
 * @Data:2021/4/15 15:01
 * @Description: TODO
 * @Version:1.0
 */
public class Person {
    private  String name;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello(){
        System.out.println("My name is "+name);
    }
}
