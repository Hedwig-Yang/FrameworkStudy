package com.atguigu.mybatis.beans;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Author:KUN
 * @Data:2021/7/22 10:39
 * @Description: 数据库工共测试类
 * @Version:1.0
 */
public class PublicTestBean {
    private int id;
    private String userName;
    private int age;
    private String gender;
    private String school;
    private Date birthday;
    private Timestamp addTime;
    private Timestamp updateTime;

    public PublicTestBean() {}

    public PublicTestBean(int id, String userName, int age, String gender,
                          String school, Date birthday, Timestamp addTime, Timestamp updateTime) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.gender = gender;
        this.school = school;
        this.birthday = birthday;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PublicTestBean{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", scholl='" + school + '\'' +
                ", birthday=" + birthday +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
