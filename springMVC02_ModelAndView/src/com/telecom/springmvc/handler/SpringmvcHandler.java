package com.telecom.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author:KUN
 * @Data:2021/5/7 15:33
 * @Description: 学习并测试处理模型数据
 * @Version:1.0
 */
@Controller
public class SpringmvcHandler {

    /**
     * 测试：处理模型数据的第一种方式 ModelAndView
     * 结论：SpringMVC会把ModleAndView中的模型数据存放到request域对象中
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

    /**
     * 测试：处理模型数据的第二种方法 Map
     * SpringMVC在调用这个方法时会自动创建一个map对象传入方法，用于存储模型数据，之后会
     *      将map中的模型数据存放到request域对象中
     * SpringMVC在调用完请求处理方法后，不管返回值是什么类型，都会处理成一个ModelAndView对象
     * (参考DispatcherServlet的945行)
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        //模型数据：password = 123456
        map.put("password",123456);
        return "success";
    }

    /**
     * 测试：处理模型数据的第三种方法Model
     */
    @RequestMapping("/testModel")
    public String testModel(Model model){
        //模型数据：loginMsg = 用户名或者密码错误
        model.addAttribute("loginMsg","用户名或密码错误");
        return "success";
    }

    /**
     * 测试视图解析器
     */
    @RequestMapping("/testView")
    public String testView(){
        /*视图的作用是渲染模型数据，将模型里的数据以某种形式呈现给客户,同时实现转发或重定向。
        * 视图解析器的作用比较单一：将逻辑视图(视图名称)解析为一个具体的视图对象。
        */
        return "success";
    }

    /**
     * 测试重定向
     * 在返回值前加上"redirect:"的字符串，就可以实现重定向
     * 在返回值前加上"forward:"的字符串，就可以实现转发
     */
    @RequestMapping("/testRedirectView")
    public String testRedirectView(){
        //重定向的时候是不经过视图解析器的，因此无法拼接路径，需要自己提供jsp文件路径
        //return "redirect:ok.jsp";
        return "forward:ok.jsp";
    }



}
/*
 * 以前进行前端展示的做法(也是SpringMVC底层的做法)：
 *  1、将数据写入Request域对象
 *  2、转发到JSP页面
 *  3、使用El表达式取出数据
 */
