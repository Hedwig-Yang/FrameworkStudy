package com.tianyi.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author:Z
 * @Data:2021/11/3 16:43
 * @Description: 首页控制类
 * @Version:1.0
 */

@Controller
public class IndexController {

    /**
     * 来登录页
     */
    @GetMapping(value = {"/","/login"})
    public String longinPage(){
        return "login";
    }

    @PostMapping("/login")
    public String main(String username,String password){

        /*
         * 此时在主页面：main.html刷新时，总是会提醒“表单重复提交”
         * 原因：在login.html页面的表单提交后，默认使用站内转发到main.html,URL不变，
         *      刷新后相当于再次使用post方式，提交了一次表单。
         * 解决方法：使用重定向。
         */
        return "redirect:/mainhtml";
    }

    /**
     * 去main页面
     * 1、模板引擎 templates文件夹下，所有的所有页面的解析，一定是经过请求处理，由模板引擎来解析的，因此不能被直接的请求访问。
     * 2、由于重定向无法直接请求（localhost:8080/main.html）到main.html这个模板文件，因此还需要经历一次站内转发
     */
    @GetMapping("/mainhtml")
    public String mainPage(){
        return "main";
    }
}





/**
 * 模板引擎技术（这里特指用于Web开发的模板引擎）
 *      是为了使用户界面与业务数据（内容）分离而产生的，它可以生成特定格式的文档，用于网站的模板引擎就会生成一个标准的html文档。
 *      从字面上理解模板引擎，最重要的就是模板二字，这个意思就是做好一个模板后套入对应位置的数据，最终以html的格式展示出来，这就是模板引擎的作用。
 */
