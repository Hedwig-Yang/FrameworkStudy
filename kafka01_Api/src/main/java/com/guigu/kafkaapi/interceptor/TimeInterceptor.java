package com.guigu.kafkaapi.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author:Z
 * @Date:2022/8/17 14:20
 * @Description: kafka生产消息时间拦截器，添加时间戳
 * @Version:1.0
 */
public class TimeInterceptor implements ProducerInterceptor<String, String> {

    //该方法封装进KafkaProducer.send方法中，即它运行在用户主线程中。
    // Producer确保在消息被序列化以及计算分区前调用该方法。
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return new ProducerRecord<>(record.topic(), record.key(),
                System.currentTimeMillis() + "--" + record.value());
    }


    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
