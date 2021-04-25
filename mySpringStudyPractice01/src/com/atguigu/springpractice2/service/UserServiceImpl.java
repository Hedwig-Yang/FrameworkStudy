package com.atguigu.springpractice2.service;

import com.atguigu.springpractice2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:KUN
 * @Data:2021/4/15 16:08
 * @Description: TODO
 * @Version:1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void handleAddUser() {
        //处理业务
        userDao.addUser();
    }
}
