package cn.zyk.interfacetosql.controller;

import cn.zyk.interfacetosql.entity.User;
import cn.zyk.interfacetosql.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @Author:KUN
 * @Data:2021/6/7 14:57
 * @Description: 获取user控制类
 * @Version:1.0
 */

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/user")
    public String getUsers(Model model){
        List<User> users = userMapper.findAll();

        model.addAttribute("users",users);
        System.out.println("Coming");
        return "user";
    }

}
