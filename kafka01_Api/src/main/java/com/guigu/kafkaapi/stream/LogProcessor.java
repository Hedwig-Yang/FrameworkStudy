package com.guigu.kafkaapi.stream;


import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

/**
 * @Author:Z
 * @Date:2022/8/18 15:20
 * @Description: 流处理器
 * @Version:1.0
 */
public class LogProcessor implements Processor<byte[],byte[]> {
    /* 为什么此处实现接口的泛型不是String而是byte[]?
     *     生产者经过序列化将对像传输并保存到kafa中，消费者通过反序列化将字节码数据转换成对象用于消费；
     *     由于生产者到消费者之间传输的都是字节数组，并没有反序列化的过程，因此泛型类填写byte[]，相比较
     *     String节省了一次中间的序列化和反序列化过程，更高效。
     */

    private ProcessorContext context;

    @Override
    public void init(ProcessorContext processorContext) {
        context = processorContext;
    }

    @Override
    public void process(byte[] bytes, byte[] bytes2) {
        //获取一行数据
        String value = new String(bytes2);
        //处理数据：去除字符串中的">>>>"
        value = value.replace(">>>>", "");
        bytes2 = value.getBytes();
        //发送处理后的数据
        context.forward(bytes, bytes2);
    }

    @Override
    public void punctuate(long l) {

    }

    @Override
    public void close() {

    }
}
