package com.basessm.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;


/***
 * redisTemplate 的调用
 */
@Controller
@RequestMapping("redis")
public class RedisController {

	private static Logger logger = Logger.getLogger(RedisController.class);

	@Autowired
	private StringRedisTemplate redisTemplate;// redis操作模板

	
	@RequestMapping("test1")
	public void  test1() {
		redisTemplate.opsForValue().set("lailai", "apple3");
		logger.info("日志输出:success1");
	}
	

		@RequestMapping("test2")
		public void test2() {
			redisTemplate.opsForValue().set("aliceWang","alice2");
			String a = redisTemplate.opsForValue().get("aliceWang");
			logger.info("a:" + a);
		}
}
