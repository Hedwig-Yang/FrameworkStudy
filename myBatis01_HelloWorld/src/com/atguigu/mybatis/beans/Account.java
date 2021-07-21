package com.atguigu.mybatis.beans;

/**
 * @Author:KUN
 * @Data:2021/7/20 17:00
 * @Description: 用户余额
 * @Version:1.0
 */
public class Account {
    private String username;
    private Integer balance;

    public Account(){}

    public Account(String username,Integer balance){
        this.username = username;
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
