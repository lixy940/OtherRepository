package com.kuaixin.web.controller;

import com.kuaixin.common.constant.CommonConstant;
import com.kuaixin.common.exception.CommonException;
import com.kuaixin.common.utils.ActiveMqHandle;
import com.kuaixin.common.utils.JsonBean;
import com.kuaixin.risk.api.contant.DConstant;
import com.kuaixin.risk.api.service.BusTkbAnsDtService;
import com.kuaixin.web.utils.Constants;
import com.kuaixin.web.websocket.WebSocketEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhm on 2015/7/13.
 */
@Controller
public class HomeController {
    @Autowired
    private BusTkbAnsDtService busTkbAnsDtService;

    @RequestMapping(value="/webchat/{userId}")
    public String webchat(@PathVariable String userId,HttpSession session){
        session.setAttribute(Constants.SESSION_USERID.value(), userId);
        return "websocket";
    }



    @RequestMapping(value = "/callName",produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonBean callName() {
        JsonBean jsonBean = new JsonBean();
        jsonBean.setResult(busTkbAnsDtService.getOneByLoanNo("xxxxxxxx", DConstant.INFO_PROCESS_POST));
        return jsonBean;
    }


    @Bean
    public WebSocketEndPoint webSocketEndPoint() {
        return new WebSocketEndPoint();
    }


    @RequestMapping("/auditing/{userId}")
    @ResponseBody
    public void auditing(@PathVariable String userId) {

        webSocketEndPoint().sendMessageToUser(userId, new TextMessage("xixi" + ""));

    }


    @RequestMapping("/send")
    @ResponseBody
    public void sendMessageBy(){
        Map<String, String> message = new HashMap<>();;
        for (int i = 0; i < 12000; i++) {
            if (i % 3 == 0) {
                message.put("userId", "lishuai");
                message.put("message", "lishuai消息" + i);
            } else if (i % 5 == 0) {
                message.put("userId", "litao");
                message.put("message", "litao消息" + i);
            } else {
                message.put("userId", "suiji");
                message.put("message", "suiji" + i);
            }
            try {
                ActiveMqHandle.sendMessage(CommonConstant.ACTIVEMQ_PAGE_MESSAGE, message);
            } catch (CommonException e) {
                e.printStackTrace();
            }
            message.clear();
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
