package com.guigu.kafkaapi.consumer;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.cluster.BrokerEndPoint;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Author:Z
 * @Date:2022/8/15 11:10
 * @Description: 消费者（低级API）:根据指定的Topic、Partition、Offset消费消息
 * @Version:1.0
 */
public class LowerConsumer {


    public static void main(String[] args) {
        //定义相关参数
        // broker节点的ip(kafka集群)
        List<String> brokers = new ArrayList<>();
        brokers.add("127.0.0.1");
        // 端口
        int port = 9092;
        // topic
        String topic = "first";
        // 分区
        int partition = 0;
        // offset
        long offset = 2;

        LowerConsumer lowerConsumer = new LowerConsumer();
        lowerConsumer.getData(brokers, port, topic, partition, offset);

    }


    //获取指定分区leader: 遍历brokers -> 遍历topics -> 遍历partitions
    private BrokerEndPoint findLeader(List<String> brokers, int port, String topic, int partition){
        //遍历获取不同broker的元数据信息
        for(String broker : brokers){
            //创建一个消费者对象，用于获取分区leader的信息。
            SimpleConsumer simpleConsumer = new SimpleConsumer(broker, port, 1000, 1024 * 4, "getLeader");

            //创建topic元数据信息请求
            TopicMetadataRequest topicMetadataRequest = new TopicMetadataRequest(Collections.singletonList(topic));
            //消费者发送“topic元数据信息请求”
            TopicMetadataResponse topicMetadataResponse = simpleConsumer.send(topicMetadataRequest);
            //解析元数据返回值（多个topic的元数据信息)
            List<TopicMetadata> topicMetadatas = topicMetadataResponse.topicsMetadata();

            //遍历获取不同topic的元数据信息
            for(TopicMetadata topicMetadata : topicMetadatas){
                List<PartitionMetadata> partitionMetadatas = topicMetadata.partitionsMetadata();

                //遍历获取不同partition的元数据信息
                for(PartitionMetadata partitionMetadata : partitionMetadatas){
                    if(partitionMetadata.partitionId() == partition){
                        return partitionMetadata.leader();
                    }
                }
            }
        }
        return null;
    }

    //获取数据
    private void getData(List<String> brokers, int port, String topic, int partition, long offset){
        BrokerEndPoint leader = findLeader(brokers, port, topic, partition);
        if(leader == null){
            return;
        }
        String leaderHost = leader.host();
        //创建获取数据的消费者对象
        SimpleConsumer targetConsumer = new SimpleConsumer(leaderHost, port, 1000, 1024 * 4, "targetConsumer");

        //创建数据请求（fetchSize指的也是缓存大小，单位是字节数byte，此处可以级联多次addFetch(),即可以添加多个"topic--partition"的信息）
        FetchRequest fetchRequest = new FetchRequestBuilder().addFetch(topic, partition, offset, 2000).build();

        //获取多个"topic--partition"的数据返回值
        FetchResponse fetchResponse = targetConsumer.fetch(fetchRequest);

        //解析返回值，获取指定"topic--partition"的数据返回值
        ByteBufferMessageSet messageAndOffsets = fetchResponse.messageSet(topic, partition);
        //遍历打印
        for(MessageAndOffset messageAndOffset : messageAndOffsets){
            long offset1 = messageAndOffset.offset();
            ByteBuffer payload = messageAndOffset.message().payload();
            byte[] bytes = new byte[payload.limit()];
            //payload会将数据存入bytes数组
            ByteBuffer byteBuffer = payload.get(bytes);
            System.out.println(offset1 + "--" + new String(bytes));
        }
    }
}
