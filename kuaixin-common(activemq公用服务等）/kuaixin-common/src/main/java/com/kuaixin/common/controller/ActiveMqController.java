package com.kuaixin.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.kuaixin.common.constant.CommonConstant;
import com.kuaixin.common.exception.CommonException;
import com.kuaixin.common.service.ActiveMqService;
import com.kuaixin.common.utils.JsonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
 */
@RequestMapping("/mq")
@Controller
public class ActiveMqController {

    @Autowired
    private ActiveMqService activeMqService;

    /***
     * send message of serial char
     * @param topic title of topic or queue
     * @param message
     * @return
     */
    @RequestMapping(value = "/sendMessage/{topic}/{message}",produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonBean sendMessage(@PathVariable String topic ,@PathVariable String message){
        try {
            activeMqService.sendMessage(topic,message);
        } catch (CommonException e) {
            e.printStackTrace();
        }
        return new JsonBean();
    }

    /***
     * send message of map
     * @param topic title of topic or queue
     * @param msgObj message object of json type
     * @return
     */
    @RequestMapping(value = "/sendMessageMap",produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonBean sendMessageMap(String topic ,String msgObj){
        Map<String,String> map = (Map<String,String>)JSONObject.parseObject(msgObj, Map.class);
        try {
            activeMqService.sendMessage(topic, map);
        } catch (CommonException e) {
            e.printStackTrace();
        }
        return new JsonBean();
    }

    /***
     * get message of serial char
     * @param topic title of topic or queue
     * @return
     */
    @RequestMapping(value = "/getMessage",produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonBean getMessage(String topic){
        JsonBean jsonBean = new JsonBean();
        try {
            jsonBean.setResult(activeMqService.getMessage(topic));
        } catch (CommonException e) {
            e.printStackTrace();
        }
        return jsonBean;
    }

    /***
     * get message of serial char
     * @param topic title of topic or queue
     * @return
     */
    @RequestMapping(value = "/getMessageMap",produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonBean getMessageMap(String topic) {
        JsonBean jsonBean = new JsonBean();
        try {
            jsonBean.setResult(activeMqService.getMessageMap(topic));
        } catch (CommonException e) {
            e.printStackTrace();
        }
        return jsonBean;
    }
}
