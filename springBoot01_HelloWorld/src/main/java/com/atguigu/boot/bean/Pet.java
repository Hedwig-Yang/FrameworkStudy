package com.atguigu.boot.bean;

/**
 * @Author:Z
 * @Data:2021/10/15 14:11
 * @Description: 宠物
 * @Version:1.0
 */
public class Pet {
    private String name;

    public Pet(){}
    public Pet(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                '}';
    }
}
