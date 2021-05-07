package com.telecom.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:KUN
 * @Data:2021/5/7 15:33
 * @Description: 学习并测试ModelAndView
 * @Version:1.0
 */
@Controller
public class SpringmvcHandler {

    /**
     * 测试 ModelAndView
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        System.out.println("找到对应的方法");
        //模型数据：由服务器处理完成的数据需要提交到浏览器进行展示，这部分数据称为模型数据。
        //以username = Admin 为例
        ModelAndView mav = new ModelAndView();

        //添加模型数据
        mav.addObject("username","Admin");

        //设置视图信息
        mav.setViewName("success");
        return mav;


    }




}
/*
 * 以前进行前端展示的做法：
 *  1、将数据写入Request域对象
 *  2、转发到JSP页面
 *  3、使用El表达式取出数据
 */
