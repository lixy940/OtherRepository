package com.hansy.mq.auto;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2017/4/7.
 * @desc 文本监听
 */
public class TextListener implements MessageListener {

    public void onMessage(Message message) {
        try {
            TextMessage m = (TextMessage)message;
            //输出文本内容
            System.out.println(m.getText());

            //todo 自动将内容推送

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
