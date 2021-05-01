package com.telecom.springmvc.helloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:KUN
 * @Data:2021/4/30 17:15
 * @Description: 测试REST请求
 * @Version:1.0
 */

@Controller
public class SpringMVCRestHandler {

    /**
     * REST POST 增加
     */
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public String testRestGET(){
        System.out.println("REST POST");
        return "success";
    }


    /**
     * REST POST -> DELETE 删除
     */
    @RequestMapping(value = "/order/{id}",method = RequestMethod.DELETE)
    public String testRestDELETE(@PathVariable("id") Integer id){
        System.out.println("REST DELETE : "+id);
        return "success";
    }


    /**
     * REST POST -> PUT 修改
     */
    @RequestMapping(value = "/order",method = RequestMethod.PUT)
    public String testRestPUT(){
        System.out.println("REST PUT");
        return "success";
    }

    /**
     * REST GET 查询
     */
    @RequestMapping(value = "/order/{id}",method = RequestMethod.GET)
    public String testRestGET(@PathVariable("id") Integer id){
        System.out.println("REST GET : "+id);
        return "success";
    }




}
