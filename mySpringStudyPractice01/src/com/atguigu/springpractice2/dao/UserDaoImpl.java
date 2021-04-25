package com.atguigu.springpractice2.dao;

import org.springframework.stereotype.Repository;

/**
 * @Author:KUN
 * @Data:2021/4/15 16:10
 * @Description: TODO
 * @Version:1.0
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void addUser() {
        System.out.println("UserDao JDBC..............");
    }
}
