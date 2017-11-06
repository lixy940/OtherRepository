package com.fuliwd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fuliwd.dao.UserMapper;
import com.fuliwd.pojo.User;
import com.fuliwd.service.IUserService;

@Service("userService")
public class UserServiceImpl  implements IUserService{

	@Resource
	private UserMapper userMapper;

	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userMapper.selectByPrimaryKey(userId);
	}

}
