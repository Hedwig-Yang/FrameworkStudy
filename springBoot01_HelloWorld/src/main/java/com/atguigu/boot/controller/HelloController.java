package com.atguigu.boot.controller;

import com.atguigu.boot.bean.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程：尚硅谷2021版最新SpringBoot2_权威教程
 * 课程url：https://www.bilibili.com/video/BV1Et411Y7tQ?p=131&spm_id_from=pageDriver
 * 代码url：https://gitee.com/leifengyang/springboot2/blob/master/boot-05-web-admin/src/main/resources/templates/login.html
 * 课件url：https://www.yuque.com/atguigu/springboot/qb7hy2#B9J3u
 * @Author:Z
 * @Data:2021/10/14 18:26
 * @Description: 接口
 * @Version:1.0
 */

//@Controller
//@ResponseBody //类上的ResponseBody表示类内的所有接口返回字符串给浏览器
@RestController //作用等同于@Controller + @ResponseBody
@Slf4j
public class HelloController {

    @Autowired
    Car car;



    /**
     * @ResponseBody:表示接口返回数据以字符串形式返回给浏览器，而不是页面跳转。
     */
    //@ResponseBody
    @RequestMapping("/hello")
    public String handle01(){
        log.info("请求进来了");
        try{
            Thread.sleep(7000);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "{\"Hello\":\"SpringBoot2\"}";
    }

    /**
     * 测试@ConfigurationProperties(prefix = "mycar")实现配置绑定
     */
    @RequestMapping("/car")
    public Car getCar(){
        return car;
    }
}

/**
 * 总结：@ResponseBody的作用：
 *      注解@responseBody的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，
 *      写入到response对象的body区，通常用来返回JSON数据或者是XML数据。一般在异步获取数据时使用【也就是AJAX】。
 *      注意：
 *          1、在使用此注解之后不会再走视图处理器，而是直接将数据写入到输入流中，他的效果等同于通过response对象输出指定格式的数据。
 *          2、在使用 @RequestMapping后，返回值通常解析为跳转路径，但是加上 @ResponseBody 后返回结果不会被解析为跳转路径，
 *             而是直接写入 HTTP response body 中。 比如异步获取 json 数据，加上 @ResponseBody 后，会直接返回 json 数据。
 *             @RequestBody 将 HTTP 请求正文插入方法中，使用适合的 HttpMessageConverter 将请求体写入某个对象。
 */
