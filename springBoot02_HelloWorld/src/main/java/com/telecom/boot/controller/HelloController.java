package com.telecom.boot.controller;

import com.telecom.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:Z
 * @Data:2021/10/21 15:15
 * @Description: 控制类
 * @Version:1.0
 */
@Controller
public class HelloController {

    @Autowired
    private Person person;

    @ResponseBody
    @RequestMapping("/hello")
    public Person getPerson(){
        return person;
    }
}
