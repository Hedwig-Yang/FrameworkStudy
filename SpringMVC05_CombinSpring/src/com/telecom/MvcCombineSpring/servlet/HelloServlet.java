package com.telecom.MvcCombineSpring.servlet;

import com.telecom.MvcCombineSpring.beans.Person;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:KUN
 * @Data:2021/5/26 16:51
 * @Description: 创建Servlet。（SpringMVC的Handle底层也就是Servlet）
 * @Version:1.0
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //访问SpringIOC容器中的person对象
        //从ServletContext对象中获取SpringIOC容器对象
        ServletContext sc = getServletContext();
        ApplicationContext ctx = (ApplicationContext)sc.getAttribute("applicationContext");
        Person person = ctx.getBean("person", Person.class);
        person.sayHello();
    }
}

/**SpringMVC整合Spring的关键在于获取统一的SpringIOC容器
 * 在Spring中，可以在Main方法和test方法中创建IOC容器 new ClassPathXmlApplicationContext("xxx.xml")
 * 在SpringMVC,的 WEB环境中，存在多个不同的 Handle，通过new的方法获取的IOC容器各不相同。
 * 解决方法：
 *     通过监听器监听域对象ServletContext的创建，然后创建IOC容器并与ServletContext对象绑定，
 *     让所有的Web组件共享IOC容器，从而实现在请求到达处理方法以前就创建了统一的IOC容器。
 */
