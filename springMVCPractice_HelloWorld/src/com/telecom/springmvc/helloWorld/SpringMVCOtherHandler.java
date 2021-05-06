package com.telecom.springmvc.helloWorld;

import com.telecom.springmvc.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:KUN
 * @Data:2021/5/6 15:32
 * @Description: 测试其他标签
 * @Version:1.0
 */

@Controller
public class SpringMVCOtherHandler {


    /**
     * 测试标签@requestParam(映射请求参数到请求方法的形参)，获取请求参数
     * 1、如果请求参数名和形参名一致，则可以省略@RequestParam的指定
     * 2、@RequestParam标注的形参必须要赋值，必须要能从请i去对象中获取到对应的请i去参数
     *    可以使用equired来设置为不是必须的
     * 3、可以通过defaultValue来指定一个默认值取代null
     *
     * 客户端请求：testRequestParam?username=Tom&age=22
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam("username") String username,
                                   @RequestParam(value = "age",required = false,defaultValue = "0") Integer age){
        //获取请求参数的方式：request.getParameter()   request.getParameterMap()
        System.out.println(username+","+age);
        return "success";

    }


    /**
     * 测试标签@RequestHeader,获取请求头信息
     */
    @RequestMapping("/testRequestHeader")
    public String testReuqestHeader(@RequestHeader("Accept-Language")String acceptLanguage){
        System.out.println(acceptLanguage);
        return "success";
    }

    /**
     * 测试标签：@CookieValue, 映射cookie信息到请求方法的形参中
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID")String sessionId){
        System.out.println("sessionId:"+sessionId);
        return "success";
    }

    /**
     *  测试POJO对象接收请求参数
     */
    @RequestMapping("/testPOJO")
    public String testPOJO(User user){
        System.out.println(user.toString());
        return "success";
    }

    /**
     * 测试原生的Servlet API
     */
    @RequestMapping("/testServletAPI")
    public void testServletAPI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request: "+request);
        System.out.println("response: "+response);
        //return "success";

        //使用获取的request对象进行转发操作
        //request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request,response);
        //使用获取的response对象进行重定向
        //response.sendRedirect("http://www.baidu.com");
        //使用获取的response对象将数据写到前端
        response.getWriter().println("Hello SpringMVC");
    }

}

/*
 * 内部跳转(请求转发)和外部跳转(重定向)的区别?
 * 比喻：
 *     1、请求转发：
 *          A向B发送请求，想让B帮忙完成一项工作，当B接收到请求后发现自己完成不了，
 *         于是B请C帮忙，C接收到B的请求后最终完成了该项工作，并把最后的结果返回给A。此过程
 *         A只发送了一次请求给B，A只知道把请求发给B，至于B是怎么完成的A并不关心，他只等待最终的结果。
 *     2、重定向：
 *          A向B发送请求，想让B帮忙完成一项工作，当B接收到请求后发现自己无法完成，
 *         就立即告诉A情况，并向A推荐C可以完成该项工作，于是A就找C帮忙，C最终完成该项工作，返回给A。
 */
