package com.telecom.MvcCombineSpring.dao;

import org.springframework.stereotype.Repository;

/**
 * @Author:KUN
 * @Data:2021/6/23 14:15
 * @Description: 接口实现类
 * @Version:1.0
 */
@Repository
public class UserDaoImpl implements UserDao{

    public UserDaoImpl(){
        System.out.println("UserDaoImpl.....");
    }

    @Override
    public void hello() {
        System.out.println("UserDaoImpl.....Hello");
    }
}
