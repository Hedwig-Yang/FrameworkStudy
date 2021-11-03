package com.telecom.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Z
 * @Data:2021/11/2 11:07
 * @Description: 传参测试
 * @Version:1.0
 */
@Controller
public class ParameterTestController {

    //测试@PathVariable、@RequestHeader、@RequestParam、@CookieValue
    @ResponseBody
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     @PathVariable("username") String name,
                                     @PathVariable Map<String,String>pvMap,
                                     @RequestHeader("User-Agent") String userAgent,
                                     @RequestHeader Map<String,String> headers,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("inters") List<String> inters,
                                     @RequestParam Map<String,String> params,
                                     @CookieValue("Admin-Token") String adminToken,
                                     @CookieValue("Admin-Token") Cookie cookie){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        //对于多个路径变量的情况会自动封装到一个map
        map.put("pvMap",pvMap);
        //获取请求头
        map.put("userAgent",userAgent);
        //获取所有请求头信息
        //map.put("headers",headers);
        //获取请求参数
        map.put("age",age);
        map.put("inters",inters);
        //获取所有请求参数,由于map中key唯一，在params中inters只保留了一个
        map.put("params",params);
        //获取单个cookie的值
        map.put("adminToken",adminToken);
        //获取所有cookie的值
        map.put("cookie",cookie);
        System.out.println(cookie.getName()+"===>"+cookie.getValue());
        return map;
    }


    //测试获取请求体,get请求的请求参数在url中，默认post请求才有请求体
    @ResponseBody
    @PostMapping("/save")
    public Map postMethod(@RequestBody String content){
        HashMap<String, Object> map = new HashMap<>();
        map.put("content",content);
        return map;
    }


    //测试矩阵变量
    //1、请求路径：cars/sell;low=34;brand=byd,audi,yd
    //2、SpringBoot默认是禁用了矩阵变量的功能
    //      手动开启：原理。对于路径的处理。UrlPathHelper进行解析。
    //              removeSemicolonContent（移除分号内容）支持矩阵变量的
    //3、矩阵变量必须绑定在url路径变量上
    @ResponseBody
    @GetMapping("/cars/{path}")
    public Map<String, Object> carsSell(@PathVariable("path") String path,
                                        @MatrixVariable("low") Integer low,
                                        @MatrixVariable("brand") List<String> brands){
        Map<String, Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brands",brands);
        map.put("path",path);
        return map;
    }


    // /boss/1;age=20/2;age=10
    //指定路径变量名，来获取同名矩阵变量
    @ResponseBody
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empId") Integer empAge){
        Map<String,Object> map = new HashMap<>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }
}
