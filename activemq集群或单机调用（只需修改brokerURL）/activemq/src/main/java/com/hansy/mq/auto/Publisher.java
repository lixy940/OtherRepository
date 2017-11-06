package com.hansy.mq.auto;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/***
 * @desc:通过Lister实现消费队列的发布  主要采用的是topic方式
 * queue 和 topic主要区别在代码中 session.createTopic(topic)或者session.createQueue(queue)
 */
public class Publisher {

    protected static String brokerURL = "tcp://192.168.46.129:61616";
    protected static transient ConnectionFactory factory;
    protected transient Connection connection;
    protected transient Session session;
    protected transient Destination destination;
    protected transient MessageProducer producer;

    public Publisher(String topic)  {
//        logger.info("生产者:Publisher");
//        logger.info("创建连接工厂");
        try {
        factory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                brokerURL);
//        logger.info("创建连接");
        connection = factory.createConnection();
//        logger.info("打开连接");
            connection.start();
//        logger.info("创建session");
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  //创建session 不带事务
//        logger.info("创建主题:" + topic);
        destination = session.createTopic(topic); //创建topic
//        logger.info("创建publisher");
        producer = session.createProducer(destination);    //创建publisher
         //设置持久化
         producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        } catch (JMSException jmse) {
//            logger.info("关闭连接");
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
//            logger.error("mq创建topic生产消息队列异常:" + jmse.getMessage());
        }
    }

    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

   public Session getSession(){
       return session;
   }

    /**
     * 发送消息对象
     * @param message message可以为文本TextMessage,也可为对象MapMessage
     * @throws JMSException
     */
    public void sendMessage(Message message) {
        try {
//            System.out.println("Sending: " + ((ActiveMQMapMessage) message).getContentMap() + " on destination: " + destination);
            producer.send(destination, message);
//            logger.info("消息发送成功");
        } catch (JMSException e) {
//            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }




}
