package com.guigu.kafka02_kafkatemplate.producer;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PostConstruct;

/**
 * @Author:Z
 * @Date:2022/8/22 15:24
 * @Description: 生产者
 * @Version:1.0
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired(required = false)
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 自定义topic
     */
    public static final String TOPIC = "second";

    /**
     * 自定义topic组
     */
    public static final String TOPIC_GROUP1 = "topic.group1";

    @PostConstruct
    public void send() {
        String message = "this is auto send message test";
        String obj2String = JSONObject.toJSONString(message);
        log.info("准备发送消息为：{}", obj2String);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(TOPIC + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info(TOPIC + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }
}
