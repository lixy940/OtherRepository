package com.kuaixin.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.kuaixin.common.constant.CommonConstant;
import com.kuaixin.common.exception.CommonException;
import com.kuaixin.common.exception.StatusMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
 * @desc httpclient request of mq
 */
public class ActiveMqHandle {

    private static Logger logger = Logger.getLogger(ActiveMqHandle.class);

    /***
     * send message of serial char
     * @param topic title of topic or queue
     * @param message content of sending message
     * @return
     */
    public static void sendMessage(String topic, String message) throws CommonException{
        if(StringUtils.isBlank(topic))
            throw new CommonException(StatusMessage.NOT_ALLOWED_NULL, "【topic/queue title is not null or empty】");

        if(StringUtils.isBlank(message))
            throw new CommonException(StatusMessage.NOT_ALLOWED_NULL, "【message is not null or empty】");

        logger.info("send topic/queue message:" + message);
        ActivemqUtil.sendMessage(topic, message);
    }

    /***
     * send message of serial char
     * @param topic title of topic or queue
     * @param map content map of sending message
     * @return
     */
    public static void sendMessage(String topic, Map<String,String> map) throws CommonException{
        if(StringUtils.isBlank(topic))
            throw new CommonException(StatusMessage.NOT_ALLOWED_NULL, "【topic/queue title is not null or empty】");

        if (null==map||map.isEmpty())
            throw new CommonException(StatusMessage.NOT_ALLOWED_NULL, "【message is not null or empty】");

        Session session = ActivemqUtil.getSession();
        MapMessage mapMessage = null;
        try {
            mapMessage = session.createMapMessage();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                mapMessage.setString(entry.getKey(),entry.getValue());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

        logger.info("【send topic/queue message:" + JSONObject.toJSONString(map) + "】");

        ActivemqUtil.sendMessage(topic, mapMessage);
    }

    /***
     * get message of serial char
     * @param topic title of topic or queue
     * @return
     */
    public static String getMessage(String topic) throws CommonException{
        if(StringUtils.isBlank(topic))
            throw new CommonException(StatusMessage.NOT_ALLOWED_NULL, "【topic/queue title is not null or empty】");

        logger.info("【get topic/queue message :the topic is " + topic + "】");

        return ActivemqUtil.getMessage(topic);
    }
    /***
     * get message map of mq
     * @param topic  title of topic or queue
     * @return
     */
    public static HashMap getMessageMap(String topic)throws CommonException {
        if(StringUtils.isBlank(topic))
            throw new CommonException(StatusMessage.NOT_ALLOWED_NULL, "【topic/queue title is not null or empty】");

        logger.info("【get topic/queue messageMap :the topic is " + topic + "】");
        MapMessage mapMessage = ActivemqUtil.getMessageMap(topic);
        HashMap<String, String> map = new HashMap<>();
        try {
            if(mapMessage!=null) {
                Enumeration mapNames = mapMessage.getMapNames();
                while (mapNames.hasMoreElements()) {
                    String key = (String) mapNames.nextElement();
                    map.put(key, mapMessage.getString(key));
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return map;
    }

}
