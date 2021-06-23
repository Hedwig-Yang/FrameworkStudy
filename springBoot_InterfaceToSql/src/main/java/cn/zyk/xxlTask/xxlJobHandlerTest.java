package cn.zyk.xxlTask;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @Author:KUN
 * @Data:2021/6/8 14:25
 * @Description: 测试定时任务
 * @Version:1.0
 */

@Component
public class xxlJobHandlerTest {


    @XxlJob("beanMethodJobHandler")
    public ReturnT<String> beanMethodJobHandler(String param) throws Exception {
        XxlJobHelper.log("bean method jobhandler running...");
        return ReturnT.SUCCESS;
    }


    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        // default success
    }

}
