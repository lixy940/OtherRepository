package com.fuliwd.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {

    public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        try {
            System.out.println("�������ܵ���Ϣ��" + textMsg.getText());
            System.out.println("��ʼ���н���������serviceִ��....");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}