package com.telecom.MvcCombineSpring.service;

import com.telecom.MvcCombineSpring.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:KUN
 * @Data:2021/6/23 14:10
 * @Description: 实现类
 * @Version:1.0
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public UserServiceImpl(){
        System.out.println("UserServiceImpl.....");
    }

    @Override
    public void hello() {
        userDao.hello();
    }
}
