package com.telecom.MvcCombineSpring.servlet;

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
@WebServlet(name = "HelloServlet")
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //访问SpringIOC容器中的person对象
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

/**SpringMVC整合Spring的关键在于获取统一的SpringIOC容器
 * 在Spring中，可以在Main方法和test方法中创建IOC容器 new ClassPathXmlApplicationContext("xxx.xml")
 * 在SpringMVC,的 WEB环境中，存在多个不同的 Handle，通过new的方法获取的IOC容器各不相同。
 * 解决方法：通过监听器监听域对象ServletContext的创建，然后将IOC容器与其绑定
 */
