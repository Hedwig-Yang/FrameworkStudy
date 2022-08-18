package com.guigu.kafkaapi.stream;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.processor.TopologyBuilder;

import java.util.Properties;

/**
 * @Author:Z
 * @Date:2022/8/18 10:41
 * @Description: kafka流式处理
 *              1、处理过程：Producer生产数据并保存到kafka的Topic：first
 *              2、流处理器Processor从Topic：first中读取数据，并对数据清洗
 *              3、Processor将清洗后的数据存入topic：second
 *              4、Customer从topic：second取出数据用于消费
 */

/*
 *     事件流是无边界数据集的抽象表示，无边界意味着无限和持续增长。随着时间的推移，新的记录会不断加入进来，比如信用卡交易、
 * 股票交易、包裹递送、游戏里物体的移动等等。除了没有边界外，事件流模型还有其他一些属性：
 * 1、有序性：事件的发生总是有个先后顺序。以金融活动事件为例，先将钱存进账户后再花钱，这与先花钱再还钱的次序是完全不一样的。
 * 2、不可变：事件一旦发生，就不能被改变。 一个金融交易被取消，并不是说它就消失了，相反，这需要往事件流里添加一个额外的事件，
 *           表示前一个交易的取消操作。
 * 3、可重播：数据要保存一段时间，不能是处理完了就删除了。重播发生在几个月前（甚至几年前）的原始事件流是一个很重要的需求。
 *           可能是为了尝试使用新的分析方法纠正过去的错误，或是为了进行审计。
 *
 * 什么叫流式处理：
 *     流式处理是指实时地处理一个或多个事件流，流式处理是一种编程范式，是一种思想。流的定义不依赖任何一个特定的框架、API或特性。
 * 只要持续地从一个无边界的数据集读取数据，然后对它们进行处理并生成结果，那就是在进行流式处理。重点是，整个处理过程必须是持续的。
 * kafka流式处理的特点
 */

public class KafkaStream {
    public static void main(String[] args) {

        //创建拓扑对象
        TopologyBuilder builder = new TopologyBuilder();

        //创建配置文件
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("application.id", "kafkaStream");

        //构建拓扑结构: 数据源source -> 缓存槽sink -> processor处理器
        ProcessorSupplier processorSupplier = () -> new LogProcessor();
        builder.addSource("SOURCE","first");
        builder.addProcessor("PROCESSOR", processorSupplier, "SOURCE");
        builder.addSink("SINK", "second","PROCESSOR");

        //启动流处理
        KafkaStreams kafkaStreams = new KafkaStreams(builder, properties);
        kafkaStreams.start();
    }
}
