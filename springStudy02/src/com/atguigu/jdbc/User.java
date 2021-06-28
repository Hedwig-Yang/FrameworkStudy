package com.atguigu.jdbc;

import java.sql.Date;

/**
 * @Author:KUN
 * @Data:2021/4/21 17:42
 * @Description: mysql一条数据对应的POJO类
 * @Version:1.0
 */
public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

    public User() {
    }

    public User(Integer id, String username, Date birthday, String sex, String address) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
/* java.sql.Date和java.util.Date区别及使用
 * java.util.Date 是 java.sql.Date 的父类（注意拼写） 前者是常用的表示时间的类，我们通常格式化或者
 * 得到当前时间都是用他“规范化”的java.sql.Date只包含年月日信息，时分秒毫秒都会清零。格式类似：YYYY-MM-DD。
 */
