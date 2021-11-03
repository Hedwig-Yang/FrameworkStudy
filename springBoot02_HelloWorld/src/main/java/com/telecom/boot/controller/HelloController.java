package com.telecom.boot.controller;

import com.telecom.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.reactive.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * @Author:Z
 * @Data:2021/10/21 15:15
 * @Description: 控制类
 * @Version:1.0
 */
@Controller
public class HelloController {

    @Autowired
    private Person person;

    @ResponseBody
    @RequestMapping("/hello")
    public Person getPerson(){
        return person;
    }


    /**
     * 若静态资源列表一个资源名lyd.jpeg和访问的动态请求名"/lyd.jpeg"相同，那浏览器输入相同的url，先访问哪个资源呢？
     * 请求进来，先去找Controller看能不能处理。不能处理的所有请求又都交给静态资源处理器。静态资源也找不到则响应404页面
     * 当 Controller匹配到URL请求时
     *      1、没有@ResponseBody注解时，由视图解析器拼凑返回值成物理地址，然后进行页面的跳转
     *      2、有@ResponseBody注解时，将返回值转换成Json字符串，响应给浏览器端。
     */
    @ResponseBody
    @RequestMapping("/lyd.jpeg")
    public String testStaticResourcesContend(){
        return "aaaa.jpeg";
    }


    /**
     * RESTful风格
     *  内容：REST描述的是在网络中client和server的一种交互形式，即：URL定位资源，用HTTP动词（GET,POST,DELETE,PUT）描述操作。
     *  使用：
     *      表单提交时：1、需要带上_method参数。2、配置文件中开启REST功能：hiddenmethod·filter·enabled：true
     *      使用客户端工具（如PostMan），支持在应用层发送PUT、DELETE请求，因此无需Filter。
     *  原理：(表单提交要使用REST的时候)
     *      1、请求过来被HiddenHttpMethodFilter拦截
     *      2、请求是否正常，并且是POST
     *      3、获取到_method的值（兼容以下请求；PUT.DELETE.PATCH）
     *      4、原生request（post），包装模式requesWrapper重写了getMethod方法，返回的是传入的值。
     *      5、过滤器链放行的时候用wrapper。以后的方法调用getMethod是调用requesWrapper的。
     */
    @ResponseBody
    //@RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser(){
        return "GET-张三";
    }

    @ResponseBody
    //@RequestMapping(value = "/user",method = RequestMethod.POST)
    @PostMapping("/user")
    public String saveUser(){
        return "POST-张三";
    }


    @ResponseBody
    //@RequestMapping(value = "/user",method = RequestMethod.PUT)
    @PutMapping("/user")
    public String putUser(){
        return "PUT-张三";
    }

    @ResponseBody
    //@RequestMapping(value = "/user",method = RequestMethod.DELETE)
    @DeleteMapping("/user")
    public String deleteUser(){
        return "DELETE-张三";
    }




    /*@Bean
    @ConditionalOnMissingBean(HiddenHttpMethodFilter.class)
    @ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = "enabled", matchIfMissing = false)
    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter();
    }*/


    //自定义filter
    /*@Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_m");
        return methodFilter;
    }*/

}

/**
 * 注意：
 *      form表单的提交方式只支持GET或者POST，为了实现restful风格，需要使用form表单实现PUT和DELETE方式的提交，
 *      对于这种情况，spring提供了过滤器 HiddenHttpMethodFilter ，可以将POST方式提交的表单转换成PUT或者DELETE。
 */
