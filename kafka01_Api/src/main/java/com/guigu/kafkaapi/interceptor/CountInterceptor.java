package com.guigu.kafkaapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author:Z
 * @Date:2022/8/17 15:02
 * @Description: kafka生产消息计数拦截器
 * @Version:1.0
 */
@Slf4j
public class CountInterceptor implements ProducerInterceptor<String, String> {

    private int successCount = 0;
    private int errorCount = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    //此方法会在消息被应答或消息发送失败时调用，并且通常都是在producer回调逻辑callback()触发之前。
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if(exception == null){
            successCount++;
        }else{
            errorCount++;
        }
    }

    @Override
    public void close() {
        log.info("发送成功：{}条；发送失败：{}条", successCount, errorCount);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
