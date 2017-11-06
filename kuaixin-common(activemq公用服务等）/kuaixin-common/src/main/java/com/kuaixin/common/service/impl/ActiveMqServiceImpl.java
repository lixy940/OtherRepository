package com.kuaixin.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kuaixin.common.exception.CommonException;
import com.kuaixin.common.exception.StatusMessage;
import com.kuaixin.common.service.ActiveMqService;
import com.kuaixin.common.utils.ActiveMqHandle;
import com.kuaixin.common.utils.ActivemqUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/24.
 * @desc local service of activemq
 */
@Component
@Transactional
public class ActiveMqServiceImpl implements ActiveMqService {
    private static Logger logger = Logger.getLogger(ActiveMqServiceImpl.class);

    @Override
    public void sendMessage(String topic, String text) throws CommonException{
        ActiveMqHandle.sendMessage(topic,text);
    }

    @Override
    public void sendMessage(String topic, Map<String,String> map) throws CommonException{
        ActiveMqHandle.sendMessage(topic,map);
    }

    @Override
    public String getMessage(String topic) throws CommonException{
        return ActiveMqHandle.getMessage(topic);
    }

    @Override
    public HashMap getMessageMap(String topic) throws CommonException{
        return ActiveMqHandle.getMessageMap(topic);
    }
}