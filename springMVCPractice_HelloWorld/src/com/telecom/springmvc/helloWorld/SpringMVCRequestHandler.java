package com.telecom.springmvc.helloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:KUN
 * @Data:2021/4/27 18:04
 * @Description: 请求处理器/请求控制器
 * @Version:1.0
 */

@Controller  //标识为请求处理器
public class SpringMVCRequestHandler {

    /**
     * 处理客户端的请求： http://localhost:8888/springMVCPractice_HelloWorld/hello
     * @RequestMapping: 完成请求和请求处理方法的映射
      */
    @RequestMapping("/hello")
    public String handleHello(){
        System.out.println("Hello SpringMVC");
        //通过视图解析器解析得到具体的视图，再去往该视图
        return "success";
    }

    /**
     * 设置映射请求方式
     * @RequestMappingMethod
     */
    @RequestMapping(value = "/testRequestMappingMethod",method = {RequestMethod.GET,RequestMethod.POST})
    public String testRequestMappingMethod(){
        return "success";
    }

    /**
     * 测试请求头带参数请求
     * params:username=tom&age=22
     * params参数用于限制请求参数中带有的参数，或者不能带有的参数
     */
    @RequestMapping(value = "/testRequestMappingParamsAndHeaders",params = {"username","age"})
    public String testRequestMappingParamsAndHeaders(){
        return "success";
    }

    /**
     * 测试带占位符的URL
     *浏览器请求URL：http://localhost:8080/springMVCPractice_HelloWorld/testPathVariable/admin/1001
     */
    @RequestMapping(value = "/testPathVariable/{name}/{id}")
    public String testPathVariable(@PathVariable("name")String name, @PathVariable("id")Integer id){
        System.out.println(name+":"+id);
        return "success";
    }



}
