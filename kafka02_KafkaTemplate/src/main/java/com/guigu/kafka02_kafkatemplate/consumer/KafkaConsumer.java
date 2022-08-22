package com.guigu.kafka02_kafkatemplate.consumer;

import com.guigu.kafka02_kafkatemplate.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author:Z
 * @Date:2022/8/22 15:21
 * @Description: 消费者
 * @Version:1.0
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = KafkaProducer.TOPIC, groupId = KafkaProducer.TOPIC_GROUP1)
    public void topicTest(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("topic_test 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }
}
