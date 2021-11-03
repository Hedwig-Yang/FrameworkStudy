package com.telecom.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Z
 * @Data:2021/11/2 16:06
 * @Description: 请求控制类
 * @Version:1.0
 */

@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        /*一般情况下，控制器方法返回字符串类型的值会被当成逻辑视图名处理,如果返回的字符串中带
        forward:或 redirect: 前缀时，SpringMVC 会对他们进行特殊处理：将 forward: 和 redirect:
        当成指示符，其后的字符串作为 URL 来处理，（因此转发和重定向时，不经过视图解析器）*/
        //转发还是一次请求，所以请求中的域对象可以共享数据
        request.setAttribute("msg","成功了........");
        request.setAttribute("code",200);
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("msg") String msg,
                       @RequestAttribute("code") Integer code,
                       HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        Object msg1 = request.getAttribute("msg");
        map.put("annotation_code",code);
        map.put("reqMethod_msg",msg1);
        map.put("annotation_msg",msg);
        return map;
      }


    /**
     * 查询方式：queryString查询字符串。获取参数方式;@RequestParam
     *      /cars/{path}?xxx=xxx&aaa=ccc
     * 查询方式：
     *      /cars/sell;low=34;brand=byd,audi,yd;
     * 矩阵变量使用场景：
*         页面开发时，cookie禁用了，session里面的内容怎么使用？
     *     正常情况：
     *         1、服务器端session存放一个值：session.set(a,b)--->
     *         2、jsessionid存放在cookie中 ---->
     *         3、每次发请求携带jsessionid。（由此判断是同一个用户的请求）
     *     Cookie被禁用：
     *          url重写：/abc;jsesssionid=xxxx 把cookie的值使用矩阵变量的方式进行传递.
     *          从而与普通的查询字符串的请求参数传参方式进行区分
     */
}
