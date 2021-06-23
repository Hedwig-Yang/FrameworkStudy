package cn.zyk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:KUN
 * @Data:2021/6/7 14:06
 * @Description:综合配置文件
 * @Version:1.0
 */

@Configuration
public class BasicConfig {

    //MySQL数据库配置
    @Value("${spring.datasource.driver-class-name}")
    private String mysqlDriver;
    @Value("${spring.datasource.url}")
    private String mySqlUrl;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    public String getMysqlDriver() {
        return mysqlDriver;
    }

    public String getMySqlUrl() {
        return mySqlUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
