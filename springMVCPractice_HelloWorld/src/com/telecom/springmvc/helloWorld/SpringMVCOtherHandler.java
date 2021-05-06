package com.telecom.springmvc.helloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
