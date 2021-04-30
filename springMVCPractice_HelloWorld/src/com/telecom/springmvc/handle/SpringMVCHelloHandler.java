package com.telecom.springmvc.handle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:KUN
 * @Data:2021/4/29 16:29
 * @Description: 测试 @RequestMapping 注解可以标注的位置（方法上、类上）
 * @Version:1.0
 */
@Controller
@RequestMapping(value = "/springmvc")
public class SpringMVCHelloHandler {

    /**
     * @RequestMapping
     * 只配置在方法上的@RequestMapping是相对于web应用的根目录的
     * 配置在类上的url@RequestMapping相当于作用在web应用的根目录下，同时作用于该类的所有方法
     */
    @RequestMapping("/testRequestMapping")
    public String testRequestMapping(){
        System.out.println("找到testRequestMapping");
        return "success";
    }

}
