package com.basessm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basessm.model.User;
import com.basessm.service.UserServiceI;

@Controller
@RequestMapping("redisTest")
public class RedisTestCon {
	
	@Autowired
	private UserServiceI userService;
	
	@RequestMapping("/addUser")
	public void addUser(){
		User user = new User();
		user.setId(6);
		user.setUsername("tom6");
		user.setPassword("123456");
		int a = userService.add(user);
		System.out.println("添加用户测试：" + a);
	}
	
	@RequestMapping("/selUser")
	@ResponseBody
	public User selUser(){
		User user = userService.selById(5);
		System.out.println("第一次 ：" + user.getUsername());
		User user2 = userService.selById(5);
		System.out.println("第二次 ：" + user2.getUsername());
		return user;
	}
	@RequestMapping("/sel")
	@ResponseBody
	public User sel(){
		User user = userService.selById(4);
		System.out.println("第一次 ：" + user.getUsername());
		User user2 = userService.selById(4);
		System.out.println("第二次 ：" + user2.getUsername());
		return user;
	}
	/**
	 * 修改数据,只允许修改密码
	 * @return
	 */
	@RequestMapping("/updUser")
	@ResponseBody
	public User updUser(){
		User user = new User();
		user.setId(5);
		user.setUsername("tom5");
		user.setPassword("alice");
		
		int su = userService.updat(user);
		System.out.println("su:" + su);
		System.out.println("获取修改后的：" + user.getPassword());
		User user2 = userService.selById(5);
		System.out.println("获取修改后的查询 ：" + user2.getUsername());
		return user;
	}
	
}
