package com.basessm.service;

import java.util.List;

import com.basessm.model.User;


public interface UserServiceI {

	int add(User user);
	int del(int id);
	int updat(User user);
	User selById(int id);
	List<User> selAll();
	User login(User user);
}
