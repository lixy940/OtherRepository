package com.hansy.mq.hand.run;

import com.hansy.mq.hand.ActivemqUtil;

import javax.jms.MapMessage;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/4/7.
 */
public class Consumer1 {


    public static void main(String... args) throws Exception {

//        System.out.println(ActivemqUtil.getMessage("hand"));
//        System.out.println(ActivemqUtil.getMessage("hand"));

/*        while (true) {
            System.out.println(ActivemqUtil.getMessage("hand"));
            Thread.sleep(1200l);
        }*/

       /* //对象接收
        while (true) {
            //每调一次消费一次
            MapMessage map = ActivemqUtil.getMessageMap("topic-li");
            if(map!=null) {
                String topic = map.getString("topic");
                double price = map.getDouble("price");
                double offer = map.getDouble("offer");
                boolean up = map.getBoolean("up");
                DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
                System.out.println(topic + "\t" + df.format(price) + "\t" + df.format(offer) + "\t" + (up ? "up" : "down"));
            }
            Thread.sleep(1000l);
        }*/


        while (true) {
            MapMessage map = ActivemqUtil.getMessageMap("topic-cli");
            if(map!=null){
//                map.acknowledge();
                System.out.println("userId:"+map.getString("userId")+",message:"+map.getString("message"));
            }
            Thread.sleep(1200l);
        }

    }

}
