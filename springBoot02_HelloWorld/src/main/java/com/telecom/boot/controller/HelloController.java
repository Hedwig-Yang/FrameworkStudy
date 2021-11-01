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


    /**
     * 若静态资源列表一个资源名lyd.jpeg和访问的动态请求名"/lyd.jpeg"相同，那浏览器输入相同的url，先访问哪个资源呢？
     * 请求进来，先去找Controller看能不能处理。不能处理的所有请求又都交给静态资源处理器。静态资源也找不到则响应404页面
     * 当 Controller匹配到URL请求时
     *      1、没有@ResponseBody注解时，由视图解析器拼凑返回值成物理地址，然后进行页面的跳转
     *      2、有@ResponseBody注解时，将返回值转换成Json字符串，响应给浏览器端。
     */
    @ResponseBody
    @RequestMapping("/lyd.jpeg")
    public String testStaticResourcesContend(){
        return "aaaa.jpeg";
    }
}
