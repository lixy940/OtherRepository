package com.kuaixin.web.listener;

import com.kuaixin.common.constant.CommonConstant;
import com.kuaixin.common.exception.CommonException;
import com.kuaixin.common.utils.ActiveMqHandle;
import com.kuaixin.web.websocket.WebSocketEndPoint;
import org.apache.log4j.Logger;
import org.springframework.web.socket.TextMessage;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/5/3.
 */
public class ActivemqThread implements Runnable {
    private static final Logger logger = Logger.getLogger(ActivemqThread.class);


    private WebSocketEndPoint webSocketEndPoint;

    public ActivemqThread(WebSocketEndPoint webSocketEndPoint){
        this.webSocketEndPoint = webSocketEndPoint;
    }

    @Override
    public void run() {
        logger.info("activemq消息获取启动");
        try {
            while (true) {
                //每隔20s
                Thread.sleep(20000l);
                HashMap<String,String> map = ActiveMqHandle.getMessageMap(CommonConstant.ACTIVEMQ_PAGE_MESSAGE);
                if (null != map && !map.isEmpty()) {
                    String userId = map.get("userId");
                    String message = map.get("message");
                    String outStr ="【"+userId +"】你的订单"+message+"审核通过了";
                    webSocketEndPoint.sendMessageToUser(userId, new TextMessage(outStr));

                }
            }

        } catch (CommonException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}
