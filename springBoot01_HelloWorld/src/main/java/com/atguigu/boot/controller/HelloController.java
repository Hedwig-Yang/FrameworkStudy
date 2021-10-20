package com.atguigu.boot.controller;

import com.atguigu.boot.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Z
 * @Data:2021/10/14 18:26
 * @Description: 接口
 * @Version:1.0
 */

//@Controller
//@ResponseBody //类上的ResponseBody表示类内的所有接口返回字符串给浏览器
@RestController //作用等同于@Controller + @ResponseBody
public class HelloController {

    @Autowired
    Car car;



    /**
     * @ResponseBody:表示接口返回数据以字符串形式返回给浏览器，而不是页面跳转。
     */
    //@ResponseBody
    @RequestMapping("/hello")
    public String handle01(){
        return "Hello SpringBoot2";
    }

    /**
     * 测试@ConfigurationProperties(prefix = "mycar")实现配置绑定
     */
    @RequestMapping("/car")
    public Car getCar(){
        return car;
    }
}
