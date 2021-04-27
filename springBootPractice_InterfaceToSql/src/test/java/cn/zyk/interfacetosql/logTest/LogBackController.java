package cn.zyk.interfacetosql.logTest;
/**
 * @Author:KUN
 * @Data:2021/3/31 15:09
 * @Description: 学习将日志输出到本地文件
 * @Version:1.0
 */
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


@Slf4j
public class LogBackController {

    @Test
    public void test1(){
        log.debug("debug测试日志消息");
        log.info("info 测试日志信息");
        log.error("info 测试日志信息");
        log.warn("warn 测试日志信息");
    }
}