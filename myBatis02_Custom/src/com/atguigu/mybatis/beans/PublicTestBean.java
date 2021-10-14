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
    private Integer id;
    private String userName;
    private Integer age;
    private String gender;
    private String school;
    private Date birthday;
    private Timestamp addTime;
    private Timestamp updateTime;
    private Integer mId;

    private Major major;

    public PublicTestBean() {}

    public PublicTestBean(Integer id, String userName, Integer age, String gender,
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

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "PublicTestBean{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", school='" + school + '\'' +
                ", birthday=" + birthday +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", mId=" + mId +
                ", major=" + major +
                '}';
    }
}
