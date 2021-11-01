package com.telecom.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * @Author:Z
 * @Data:2021/11/1 16:14
 * @Description: Web配置类
 * @Version:1.0
 */

//使用轻量模式创建代理类
@Configuration(proxyBeanMethods = false)
public class WebConfig {


    //自定义REST过滤器
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        //自定义隐含参数名，原有的是“_method”
        hiddenHttpMethodFilter.setMethodParam("_m");
        return hiddenHttpMethodFilter;
    }

}
