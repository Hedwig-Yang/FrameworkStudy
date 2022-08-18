package com.guigu.kafkaapi.producer;

import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @Author:Z
 * @Date:2022/8/11 14:40
 * @Description: 创建生产者（新API）:1、发送消息时，使用回调函数；2、绑定自定义分区
 * @Version:1.0
 */
public class CustomerProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        //kafka集群
        properties.put("bootstrap.servers","localhost:9092");
        //应答级别
        properties.put("acks","all");
        //重试次数
        properties.put("retries",0);
        /*当单次缓存数据达到设定的size，或者等待时间达到ms*/
        //批量大小
        properties.put("batch.size",16384);
        //提交延时
        properties.put("linger.ms",1);
        //缓存：整个producer可以缓存的数据:32M
        properties.put("buffer.memory",33554432);
        // key序列化
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //建立关联，设置自定义分区
        properties.put("partitioner.calss","com.guigu.kafkaapi.producer.CustomPartitionProducer");
        //关联拦截器（注意：拦截器添加顺序就是拦截器执行顺序）
        ArrayList<String> reference = new ArrayList<>();
        reference.add("com.guigu.kafkaapi.interceptor.TimeInterceptor");
        reference.add("com.guigu.kafkaapi.interceptor.CountInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, reference);


        Producer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 10; i++) {
            //producer.send(new ProducerRecord<String, String>("first", Integer.toString(i), "hello world-" + i));
            //发送结束后会调用回调函数，ProducerRecord：发送给Kafka Broker的key/value值对
            producer.send(new ProducerRecord<>("first", Integer.toString(i)), (recordMetadata, exception) -> {
                //recordMetadata:原数据信息；exception：发送失败会产生异常
                if(exception == null){
                    System.out.println("分区:"+recordMetadata.partition() + "--" + "偏移量:" + recordMetadata.offset());
                }else{
                    System.out.println("发送失败");
                }
            });
        }
        //关闭资源
        producer.close();
    }
}
