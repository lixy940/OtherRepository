package com.hansy.mq.auto.run;

import com.hansy.mq.auto.Consumer;
import com.hansy.mq.auto.MapListener;

import javax.jms.JMSException;

/**
 * Created by Administrator on 2017/4/7.
 */
public class conMain {

    public static void main(String[] args) throws JMSException {
        //对象输出 MapListener
        Consumer consumer1 = new Consumer("topic_li");
        consumer1.getMessageConsumer().setMessageListener(new MapListener());
    }
}
