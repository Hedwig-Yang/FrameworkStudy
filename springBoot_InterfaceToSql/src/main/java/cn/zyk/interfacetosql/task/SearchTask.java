package cn.zyk.interfacetosql.task;

import cn.zyk.interfacetosql.entity.User;
import cn.zyk.interfacetosql.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @Author:KUN
 * @Data:2021/6/8 10:40
 * @Description: 测试定时任务
 * @Version:1.0
 */

@Configuration
@EnableScheduling   // 2.开启定时任务
public class SearchTask {
    @Autowired
    UserMapper userMapper;





    //@Scheduled(cron = "0/5 * * * * ?")
    public void getFristInf(){
        List<User> users = userMapper.findAll();
        System.out.println(users.toArray()[0].toString());
    }
}
