package com.telecom.MvcCombineSpring.listener; /**
 * @Author:KUN
 * @Data:2021/5/26 17:59
 * @Description: 监听ServletContext的生命周期
 * @Version:1.0
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


//@WebListener()
//注释掉自建的监听器，测试使用SpringIOC容器自带的监听器见web.xml配置
public class MyServletContextListener implements ServletContextListener{

    public MyServletContextListener() {
    }

    /**
     * 当监听到ServletContet被创建，则执行该方法
     */
    public void contextInitialized(ServletContextEvent sce) {
        //创建SpringIOC容器对象
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        //将SpringIOC容器对象绑定到ServletContext中
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("applicationContext",ctx);
        System.out.println("起作用了*************************");
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
