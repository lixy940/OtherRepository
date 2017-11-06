package com.kuaixin.common.constant;


import com.kuaixin.common.utils.PropertiesLoader;

/**
 * Created by Administrator on 2017/4/17.
 */
public class CommonConstant {
    private static PropertiesLoader propertiesLoader = new PropertiesLoader("common.properties");

    //brokerURL of activemq
    public final static String ACTIVEMQ_BROKERURL = propertiesLoader.getProperty("activeMq.brokerURL");
    //activemq cluster brokerURL
    public final static String ACTIVEMQ_CLUSTER_BROKERURL = propertiesLoader.getProperty("activeMq.cluster.brokerURL");

    //page message
    public final static String ACTIVEMQ_PAGE_MESSAGE = "activemq_page_message";
    //request path
    public final static String COMMON_BASE_PATH = propertiesLoader.getProperty("common.base.path");

    //get message path
    public final static String GET_MESSAGE_PATH = "/mq/getMessage";
    //get message map path
    public final static String GET_MESSAGE_MAP_PATH = "/mq/getMessageMap";
    //send message path
    public final static String SEND_MESSAGE_PATH = "/mq/sendMessage";
    //send message path
    public final static String SEND_MESSAGE_MAP_PATH = "/mq/sendMessageMap";


}
