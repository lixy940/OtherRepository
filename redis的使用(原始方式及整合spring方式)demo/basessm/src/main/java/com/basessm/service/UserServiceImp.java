package com.basessm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basessm.dao.UserMapper;
import com.basessm.model.User;



@Service("UserServiceI")
public class UserServiceImp implements UserServiceI{
	/**
	 * 注入dao 
	 */
    @Autowired
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	public int add(User user) {
		// TODO Auto-generated method stub
		return userMapper.add(user);
	}


	public int del(int id) {
		// TODO Auto-generated method stub
		return userMapper.del(id);
	}

	public int updat(User user) {
		// TODO Auto-generated method stub
		return userMapper.update(user);
	}
	

	public User selById(int id) {
		// TODO Auto-generated method stub
		return userMapper.selById(id);
	}

	public List<User> selAll() {
		// TODO Auto-generated method stub
		return userMapper.selAll();
	}

	
	public User login(User user) {
		// TODO Auto-generated method stub
		return userMapper.login(user);
	}

	
}
