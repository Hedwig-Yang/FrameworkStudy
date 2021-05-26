package com.telecom.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:KUN
 * @Data:2021/5/25 17:49
 * @Description: 测试拦截器（取别于过滤器在Springmvc的前端控制器：DispatcherServlet之前执行，拦截器在之后执行）
 * @Version:1.0
 */
//@Component
public class MySecondInterceptor implements HandlerInterceptor {
    /**
     *1.在DispacherServlet的939行，请求处理方法之前执行。
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("[MySecondInterceptor preHandle]");
        return true;
    }

    /**
     *2.在DispacherServlet的959行，请求处理方法之后，视图处理之前执行。
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("[MySecondInterceptor postHandle]");
    }

    /**
     *3.
     * [1]在DispacherServlet的1030行，视图处理之后（转发/重定向之前执行）。
     * [2]如果当前拦截器的prehandle返回false后，也会执行当前拦截器之前拦截器的afterCompletion
     * [3]当DispatcherServlet的doDispath方法抛出异常，存在拦截器的情况下，会执行afterCompletion
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("[MySecondInterceptor afterCompletion]");
    }
}
