package com.fuliwd.controler;

import java.util.*;

import javax.jms.Destination;

import com.alibaba.fastjson.JSONObject;
import com.fuliwd.pojo.TestEntity;
import com.fuliwd.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * http://localhost:8161/admin 访问mq页面
 */
@Controller
@RequestMapping("jms")
public class JmsTestController {

    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @RequestMapping("test")
    public @ResponseBody String testSend() throws Exception {

        //系统业务需要， 需要更新用户表中信息，根据id更新name
        List<TestEntity> list = new LinkedList<TestEntity>();

        TestEntity en = new TestEntity();
        en.setId(100);
        en.setName("name1");
        list.add(en);

        TestEntity en2 = new TestEntity();
        en2.setId(1002);
        en2.setName("name2");
        list.add(en2);

        Map<String,Object> mapEntity = new HashMap<String, Object>();
        mapEntity.put("user", list);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("update", mapEntity);

        String objStr = JSONObject.toJSONString(map);

        System.out.println("发送方发送内容为：" + objStr);
        producerService.sendMessage(destination, new Date()+"-->"+objStr);
        return "jms exute complete";
    }
}
