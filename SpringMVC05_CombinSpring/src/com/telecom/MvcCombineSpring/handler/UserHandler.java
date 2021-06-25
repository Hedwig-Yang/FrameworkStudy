package com.telecom.MvcCombineSpring.handler;

import com.telecom.MvcCombineSpring.beans.Person;
import com.telecom.MvcCombineSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @Author:KUN
 * @Data:2021/6/23 14:03
 * @Description: 控制器
 * @Version:1.0
 */

@Controller
public class UserHandler {

    @Autowired
    private UserService userService;

    public UserHandler(){
        System.out.println("UserHandler.....");
    }


    @RequestMapping("/hello")
    public String hello(HttpSession session){
        //测试从MVC到IOC跨容器访问对象
        userService.hello();

        //测试获取通过Spring自带的IOC容器监听器绑定的IOC容器
        ServletContext sc = session.getServletContext();
        //通过默认Key值获取
        //ApplicationContext ctx = (ApplicationContext)sc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        //通过工具类获取
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        Person person = ctx.getBean("person", Person.class);
        person.sayHello();

        return "success";
    }
}

/*
 * IOC容器和MVC容器的关系：
 * 两者类型一致，都是XmlWebApplicationContext,IOC容器是父容器，MVC容器是子容器，
 * 子容器可以访问父容器，但是父容器不能访问子容器。
 */
