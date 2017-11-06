package com.hansy.mq.auto.run;

import com.hansy.mq.auto.Publisher;

import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2017/4/7.
 */
public class pubMain {


    public static  void main(String...args)throws Exception {
        Publisher publisher = new Publisher("topic_li");

       Session session = publisher.getSession();
        //发送对象
         for (int i = 0; i < 10; i++) {
            MapMessage message = session.createMapMessage();
            message.setString("topic", "topic");
            message.setDouble("price", 12 + i);
            message.setDouble("offer", 0.11 + i);
            message.setBoolean("up", true);
            publisher.sendMessage(message);
            Thread.sleep(2000l);
        }
        //发动字符串
//        for(int i=0;i<10;i++) {
//            TextMessage message = session.createTextMessage("字符串" + i);
//            publisher.sendMessage(message);
//           Thread.sleep(2000l);
//        }

    }
}
