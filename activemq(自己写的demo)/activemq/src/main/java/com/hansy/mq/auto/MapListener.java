package com.hansy.mq.auto;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/4/7.
 * @desc 消息对象监听
 */
public class MapListener implements MessageListener {

    public void onMessage(Message message) {
        try {
//                TextMessage m = (TextMessage)message;
            MapMessage map = (MapMessage)message;
            String topic = map.getString("topic");
            double price = map.getDouble("price");
            double offer = map.getDouble("offer");
            boolean up = map.getBoolean("up");
            DecimalFormat df = new DecimalFormat( "#,###,###,##0.00" );
            System.out.println(topic + "\t" + df.format(price) + "\t" + df.format(offer) + "\t" + (up?"up":"down"));

            //todo 自动将内容推送

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
