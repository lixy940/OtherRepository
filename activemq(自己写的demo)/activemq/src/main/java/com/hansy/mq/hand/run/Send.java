package com.hansy.mq.hand.run;

import com.hansy.mq.hand.ActivemqUtil;

import javax.jms.MapMessage;
import javax.jms.Session;

/**
 * Created by Administrator on 2017/4/7.
 */
public class Send {
    public static void main(String...args)throws Exception {
        //发送字符串
/*        ActivemqUtil.sendMessage("hand", "你好吗7");
        ActivemqUtil.sendMessage("hand", "你好吗6");
        ActivemqUtil.sendMessage("hand", "你好吗4");
        ActivemqUtil.sendMessage("hand", "你好吗1");*/

        Session session = ActivemqUtil.getSession();
        for(int i=0;i<10;i++) {
            MapMessage message = session.createMapMessage();
            message.setString("topic", "topic");
            message.setDouble("price", 12+i);
            message.setDouble("offer", 0.11+i);
            message.setBoolean("up", true);
            ActivemqUtil.sendMessage("topic-li", message);
        }

    }
}
