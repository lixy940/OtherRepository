package com.hansy.mq.hand;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @desc 手动调用数据发送和手动消费
 * topic模式为每个客户端都将收到同样的发布数据 queue只要一个客户端消费后,另外一个将不能收到同样的数据
 * queue 和 topic主要区别在代码中 session.createTopic(topic)或者session.createQueue(queue)
 * 当前是queue
 */
public class ActivemqUtil {

//    protected static String brokerURL = "tcp://192.168.46.129:61616?wireFormat.maxInactivityDuration=0";
    //集群调用
    protected static String brokerURL = "failover:(tcp://192.168.46.130:61616,tcp://192.168.46.131:61616,tcp://192.168.46.132:61616)";
    static ConnectionFactory connectionFactory;
    static Connection connection = null;
    static Session session;
    static Map<String, MessageProducer> sendQueues = new ConcurrentHashMap<String, MessageProducer>();
    static Map<String, MessageConsumer> getQueues = new ConcurrentHashMap<String, MessageConsumer>();
    static {
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                brokerURL);
        try
        {
            connection = connectionFactory.createConnection();

            connection.start();
            // 第一个参数是否使用事务:当消息发送者向消息提供者（即消息代理）发送消息时，消息发送者等待消息代理的确认，没有回应则抛出异常，消息发送程序负责处理这个错误。
            // 第二个参数消息的确认模式：
            // AUTO_ACKNOWLEDGE ：
            // 指定消息提供者在每次收到消息时自动发送确认。消息只向目标发送一次，但传输过程中可能因为错误而丢失消息。 //
            // CLIENT_ACKNOWLEDGE ：
            // 由消息接收者确认收到消息，通过调用消息的acknowledge()方法（会通知消息提供者收到了消息） //
            // DUPS_OK_ACKNOWLEDGE ：
            // 指定消息提供者在消息接收者没有确认发送时重新发送消息（这种确认模式不在乎接收者收到重复的消息）。
            session = connection.createSession(Boolean.FALSE.booleanValue(),
                    Session.AUTO_ACKNOWLEDGE);

        }
        catch (Exception e) {
            e.printStackTrace();
//            log.info("消息队列异常:" + e.getMessage());
        }
    }


    //===============队列目的地模式======================================

    static MessageProducer getMessageProducer(String name) {
        if (sendQueues.containsKey(name))
            return ((MessageProducer)sendQueues.get(name));
        try
        {
            Destination destination = session.createQueue(name);
            MessageProducer producer = session.createProducer(destination);
            // 设置持久化，DeliveryMode.PERSISTENT和DeliveryMode.NON_PERSISTENT
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            sendQueues.put(name, producer);
            return producer;
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return ((MessageProducer)sendQueues.get(name));
    }

    static MessageConsumer getMessageConsumer(String name) {
        if (getQueues.containsKey(name))
            return ((MessageConsumer)getQueues.get(name));
        try
        {
            Destination destination = session.createQueue(name);
            MessageConsumer consumer = session.createConsumer(destination);
            getQueues.put(name, consumer);
            return consumer;
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return ((MessageConsumer)getQueues.get(name));
    }

    /**
     * 发送字符串
     * @param topic
     * @param text
     */
    public static void sendMessage(String topic, String text) {
        try {
            TextMessage message = session.createTextMessage(text);
            getMessageProducer(topic).send(message);
            // log.info("sendMessage " + hand + "\t\t" + text);
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /***
     * 发送对象
     * @param topic
     * @param map
     */
    public static void sendMessage(String topic, MapMessage map) {
        try {
            getMessageProducer(topic).send(map);
            // log.info("mq->sendMessage " + topic + "\t\t" + text);
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }


    /***
     * 接收字符串
     * @param topic
     * @return
     */
    public static String getMessage(String topic)
    {
        try {
            TextMessage message = (TextMessage)getMessageConsumer(topic).receive(10000L);
            if (message != null)
                return message.getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }


    /***
     * 获取对象
     * @param topic
     * @return
     */
    public static MapMessage getMessageMap(String topic)
    {
        try {
            MapMessage message = (MapMessage)getMessageConsumer(topic).receive(10000L);
            if (message != null)
                return message;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Session getSession(){
        return session;
    }

    public static void close() {
        try {
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
