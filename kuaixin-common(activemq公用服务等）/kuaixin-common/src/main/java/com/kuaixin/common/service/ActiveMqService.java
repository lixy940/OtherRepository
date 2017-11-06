package com.kuaixin.common.service;

import com.kuaixin.common.exception.CommonException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
 */
public interface ActiveMqService {
    /**
     * send character string
     * @param topic title of topic
     * @param text  character string
     */
    void sendMessage(String topic, String text)throws CommonException;

    /**
     * send map objet
     * @param topic
     * @param map
     */
    void sendMessage(String topic, Map<String,String> map)throws CommonException;

    /***
     * get character string of current topic's title
     * @param topic
     * @return
     */
    String getMessage(String topic)throws CommonException;

    /***
     * get map of current topic's title
     * @param topic
     * @return
     */
    HashMap<String,String> getMessageMap(String topic)throws CommonException;
}
