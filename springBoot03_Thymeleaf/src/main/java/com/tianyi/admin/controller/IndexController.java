package com.tianyi.admin.controller;

import com.tianyi.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpSession;

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
    public String main(User user, HttpSession session, Model model){
        /*
         * 此时在主页面：main.html刷新时，总是会提醒“表单重复提交”
         * 原因：在login.html页面的表单提交后，默认使用站内转发到main.html,URL不变，
         *      刷新后相当于再次使用post方式，提交了一次表单。
         * 解决方法：使用重定向。
         */
        if(!StringUtils.isEmpty(user.getUserName()) && "123456".equals(user.getPassword())){
            session.setAttribute("loginUser",user);
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","账号密码错误");
            return "login";
        }

    }

    /**
     * 去main页面
     * 1、模板 templates文件夹下，所有的所有页面的解析，一定是经过请求处理，由模板引擎来解析的，因此不能被直接的请求访问。
     * 2、由于重定向无法直接请求（localhost:8080/main.html）到 main.html这个模板文件，因此还需要经历一次站内转发
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){
        //判断是否登录（最好使用过滤器或者拦截器）
        if(session.getAttribute("loginUser") != null){
            return "main";
        }else{
            model.addAttribute("msg","请重新登录");
            return "login";
        }

    }
}





/**
 * 模板引擎技术（这里特指用于Web开发的模板引擎）
 *      是为了使用户界面与业务数据（内容）分离而产生的，它可以生成特定格式的文档，用于网站的模板引擎就会生成一个标准的html文档。
 *      从字面上理解模板引擎，最重要的就是模板二字，这个意思就是做好一个模板后套入对应位置的数据，最终以html的格式展示出来，这就是模板引擎的作用。
 */


/**
 * 浏览器渲染是什么？
 *  页面渲染就是浏览器将HTML变成人眼看到的图像的全过程。
 *  渲染大致分为两部分：
 *      一部分是排版，一部分是绘制。
 *      排版就是根据文档流，加上浮动、定位等属性，确定各个盒子的位置还有尺寸。
 *      绘制就是将css的属性例如字体、背景色、圆角等等按照给定的方式呈现出来。各个浏览器的渲染效率不一样。
 *  举例：
 *      你要吃个菜，你找到厨师说，我要尖椒肉丝。厨师就去翻菜谱，炒给你吃。
 *      你是浏览者；菜是你将看到的页面；厨师是浏览器；菜谱是程序员写的页面代码；炒菜的过程，就是页面渲染。
 */


/**
 * 浏览器这边做的工作大致分为以下几步：
 * 1、加载：根据请求的URL进行域名解析，向服务器发起请求，接收文件（HTML、JS、CSS、图象等）。
 * 2、解析：对加载到的资源（HTML、JS、CSS等）进行语法解析，建议相应的内部数据结构（比如HTML的DOM树，JS的（对象）属性表，CSS的样式规则等等）
 * 3、渲染：构建渲染树，对各个元素进行位置计算、样式计算等等，然后根据渲染树对页面进行渲染（可以理解为“画”元素）
 * 这几个过程不是完全孤立的，会有交叉，比如HTML加载后就会进行解析，然后拉取HTML中指定的CSS、JS等
 *
 */


/**
 * 自己的理解，请求返回分为两种
 * 1、带了@ResponseBody，直接将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，
 *    写入到response对象的body区,返回给浏览器。
 * 2、对于请求转发的情况,会有一个视图渲染的过程：view.render(modelAndView,request,response)
 *    这个过程主要是将modelAndView,request,response三个对象中的参数，填写到视图对象中，
 *    返回给客户端，这个过程称之为视图渲染。
 */
