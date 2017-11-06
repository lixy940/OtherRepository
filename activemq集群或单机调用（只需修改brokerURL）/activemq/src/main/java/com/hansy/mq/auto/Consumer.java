package com.hansy.mq.auto;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/***
 * @desc 采用lister监听方式实现消息队列的消费 主要采用topic 即每个客户端都将完整获取生产者的数据
 * queue 和 topic主要区别在代码中 session.createTopic(topic)或者session.createQueue(queue)
 */
public class Consumer {


    private static String brokerURL = "tcp://192.168.46.129:61616";
    private static transient ConnectionFactory factory;
    private transient Connection connection;
    private transient Session session;
    private transient Destination destination;
    private transient MessageConsumer messageConsumer;

    public Consumer(String topic) {
        //        logger.info("消费者:Consumer");
        try {
//        logger.info("创建连接工厂");
            factory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD,
                    brokerURL);
            //        logger.info("创建连接");
            connection = factory.createConnection();
//            logger.info("打开连接");
            connection.start();
            //        logger.info("创建session");
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //        logger.info("创建主题:" + topic);
            destination = session.createTopic(topic);
            //        logger.info("创建消费者");
            messageConsumer = session.createConsumer(destination);
        } catch (JMSException jmse) {
            try {
                connection.close();
            } catch (JMSException e1) {
                e1.printStackTrace();
            }
//            logger.err("mq创建topic消费队列异常:" + jmse.getMessage());
        }

    }

    public MessageConsumer getMessageConsumer() {
        return messageConsumer;
    }


    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }


}