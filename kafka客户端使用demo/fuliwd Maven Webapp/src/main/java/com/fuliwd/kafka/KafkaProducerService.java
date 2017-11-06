package com.fuliwd.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerService {
    private static Logger LOG = LoggerFactory
            .getLogger(KafkaProducerService.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        //zookeeper地址
        props.put("bootstrap.servers", "192.168.46.130:9092,192.168.46.131:9092,192.168.46.132:9092");
        props.put("retries", 3);
        props.put("linger.ms", 1);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(
                props);
        //test-topic为队列名称
        for (int i = 0; i < 1; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                    "test-topic", "11", "客户端调用 yoyo=======>" + i);
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    // TODO Auto-generated method stub
                    if (e != null)
                        System.out.println("the producer has a error:"
                                + e.getMessage());
                    else {
                        System.out
                                .println("The offset of the record we just sent is: "
                                        + metadata.offset());
                        System.out
                                .println("The partition of the record we just sent is: "
                                        + metadata.partition());
                    }
                }
            });
            try {
                Thread.sleep(1000);
                // producer.close();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}