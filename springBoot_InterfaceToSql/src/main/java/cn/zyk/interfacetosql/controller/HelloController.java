package cn.zyk.interfacetosql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:KUN
 * @Data:2021/6/7 14:17
 * @Description: SpringBoot运行测试
 * @Version:1.0
 */

@Controller
@ResponseBody
public class HelloController {

    @RequestMapping("/hello")
    public String HelloSpringBoot(){
        return "Hello..............";
    }
}
