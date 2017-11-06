package com.basessm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.basessm.model.User;
import com.basessm.service.UserServiceI;

@Controller
@RequestMapping("user/")
public class UserController {
	@Autowired
	private UserServiceI userService;

	@RequestMapping("/tologin")
	public String tologin(HttpServletRequest request) {

		return "user";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, @ModelAttribute("user") User user) {

		String username2 = user.getUsername();
		String password2 = user.getPassword();
		System.out.println("username :" + username2 + "  password :" + password2);
		User user1 = userService.login(user);
		if (user1 == null) {
			System.out.println("登录失败！");
		} else {
			System.out.println("登录成功！");
		}
		return "loginsucc";
	}

	/**
	 * allUser
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/allUser")
	public String allUser(HttpServletRequest request, ModelAndView model) {
		System.out.println("查询当前用户");
		User alluser = userService.selById(1);
		request.setAttribute("id", alluser.getId());
		request.setAttribute("userName", alluser.getUsername());
		request.setAttribute("password", alluser.getPassword());
		return "loginsucc";
	}

}
