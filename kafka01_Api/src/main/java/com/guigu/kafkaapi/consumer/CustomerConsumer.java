package com.guigu.kafkaapi.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.boot.logging.logback.LogbackLoggingSystem;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/**
 * @Author:Z
 * @Date:2022/8/12 11:10
 * @Description: 消费者（高级API）
 * @Version:1.0
 */
public class CustomerConsumer {
    public static Logger logger = LoggerFactory.getLogger(CustomerConsumer.class);

    public static void main(String[] args) {
        Properties props = new Properties();

        //kafka集群
        props.put("bootstrap.servers", "localhost:9092");

        //消费者组id(如果想重复消费某个同批次的数据，3个方法：
        // 1、新建一个新的消费者组；2、使用低级consumerAPI，指定offset；3、使用高级consumerAPI，指定offset；
        props.put("group.id", "testb");

        //新建组来从头消费的时候，需要设置属性"auto.offset.reset"，不然默认新建组的offset为lastest最大值。
        //"auto.offset.reset"属性，仅当offset不存在，或者offset未被初始化时，才能生效，启到重置offset的作用。
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //设置自动提交offset
        props.put("enable.auto.commit", "true");

        //设置offset提交延迟时间,作用事保证数据业务处理完成后再提交offse,
        //若延迟时间过短，offset先提交，但业务数据还未处理完，此时若客户端宕机，再重启后会丢失这一条数据，而从下一条开始读取。
        //同理若设置延迟时间过长，可能因宕机问题导致重复消费。
        //为彻底避免这一状况，最好是关闭自动提交，待业务处理结束后，手动提交offest。
        props.put("auto.commit.interval.ms", "1000");

        //key序列化
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //value序列化
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //指定Topic
        consumer.subscribe(Arrays.asList("first","second"));

        //使用高级consumerAPI，指定topic、partition、offset进行消费；
        //跟低级consumerAPI相比，高级API只能重置offset，但是不能自行维护offset（即不能像低级API一样取出offset并保存）
        /*consumer.assign(Collections.singletonList(new TopicPartition("first",0)));
        consumer.seek(new TopicPartition("first",0),2);*/

        while(true){
            //获取数据,传入参数拉取时间间隔，返回ConsumerRecords：从kafka接收的键/值对
            //如果拉到数据的话会立即放回；如果拉不到数据的话，这个是最长的等待时间；
            //比如100ms，如果一直没有数据的话，到100ms就返回，有数据就立即返回再拉
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
            for(ConsumerRecord<String, String> consumerRecord : consumerRecords){
                logger.info("消费主题：" + consumerRecord.topic() + "--"
                        + "消费分区：" + consumerRecord.partition() + "--"
                        + "消费内容：" + consumerRecord.value());
            }
        }
    }
}
