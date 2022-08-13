package com.atguigu.springpractice2.controller;

import com.atguigu.springpractice2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author:KUN
 * @Data:2021/4/15 16:05
 * @Description: UserController
 * @Version:1.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    //private static UserService userService;  无法在编译时注入

    public void regist() {
        userService.handleAddUser();
    }


}
