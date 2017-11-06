package com.basessm.dao;

import java.util.List;

import com.basessm.model.User;



public interface UserMapper {

	int add(User user);
	int del(int id);
	int update(User user);
	User selById(int id);
	List<User> selAll();
	
	User login(User user);

	
	
}
