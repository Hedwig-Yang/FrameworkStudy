package com.atguigu.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Z
 * @Data:2021/10/15 14:05
 * @Description: POJO
 * @Version:1.0
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor 对于使用部分参数生成构造器的情况，需要手动编写
public class User {

    private String name;
    private Integer age;
    private Pet pet;

    public User(String name,Integer age){
        this.name = name;
        this.age = age;
    }

/*    public User(){
    }
    public User(String name,Integer age){
        this.name = name;
        this.age = age;
    }
    public User(String name,Integer age,Pet pet){
        this.name = name;
        this.age = age;
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pet=" + pet +
                '}';
    }*/
}

/**
 * lombok使用总结
 * 常用的几个注解：
 *      @Data ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
 *      @AllArgsConstructor ： 注在类上，提供类的全参构造
 *      @NoArgsConstructor ： 注在类上，提供类的无参构造
 *      @Setter ： 注在属性上，提供 set 方法
 *      @Getter ： 注在属性上，提供 get 方法
 *      @EqualsAndHashCode ： 注在类上，提供对应的 equals 和 hashCode 方法
 *      @Log4j/@Slf4j ： 注在类上，提供对应的 Logger 对象，变量名为 log
 *优点：
 *      能通过注解的形式自动生成构造器、getter/setter、equals、hashcode、toString等方法，提高了一定的开发效率
 *      让代码变得简洁，不用过多的去关注相应的方法
 *      属性做修改时，也简化了维护为这些属性所生成的getter/setter方法等
 * 缺点：
 *      不支持多种参数构造器的重载
 *      虽然省去了手动创建getter/setter方法的麻烦，但大大降低了源代码的可读性和完整性，降低了阅读源代码的舒适度
 */
