package cn.zyk.interfacetosql.entity;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;

/**
 * @Author:KUN
 * @Data:2021/6/7 14:41
 * @Description: MySQL表中User
 * @Version:1.0
 */

@EnableScheduling   // 2.开启定时任务
public class User {
    private int id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

    @Scheduled
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Scheduled
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Scheduled
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    @Scheduled
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    @Scheduled
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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
}
