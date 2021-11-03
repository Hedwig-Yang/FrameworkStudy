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
        /*视图的作用是渲染模型数据，将模型里的数据以某种形式呈现给客户。
        * 视图解析器的作用比较单一：将逻辑视图(视图名称)解析为一个具体的视图对象。
        */
        return "success";
    }

    /**
     * 测试重定向
     * 1、在返回值前加上"redirect:"的字符串，就可以实现重定向
     * 2、在返回值前加上"forward:"的字符串，就可以实现转发
     * 3、一般情况下，控制器方法返回字符串类型的值会被当成逻辑视图名处理,如果返回的字符串中带 forward:
     *      或 redirect: 前缀时，SpringMVC 会对他们进行特殊处理：将 forward: 和 redirect: 当成指示符，
     *      其后的字符串作为 URL 来处理，（因此转发经过视图解析器，但是重定向不经过视图解析器）
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

/**
 * 什么是转发和重定向java
 *     转发是服务器行为，重定向是客户端行为。转发耗时比重定向少。
 * 小知识：WEB-INF文件夹下的任何文件都是不能经过url直接访问的。
 *     1.转发，是会经过视图解析器，进行前缀后缀添加，能够访问WEB-INF文件夹下的文件
 *     2.重定向，不会经过视图解析器，也就不会添加前缀和后缀，不能够访问WEB-INF，很好理解，
 *       由于重定向就是让浏览器经过新的URL去访问服务器，因此不能访问WEB-INF下的文件。
 */

