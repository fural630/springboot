package com.example.module.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.module.user.service.UserService;
/**
 * 用户模块
 * @author zhangzm
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转用户管理页面
	 * @return
	 */
	@RequestMapping("userManage")
	public String userManage () {
		return "user/userManage";
	}
	
	/**
	 * 获取用户列表数据
	 * @return
	 */
	@RequestMapping("getUserTable")
	@ResponseBody
	public String getUserTable () {
		System.out.println(userService.getUser("1"));
		return null;
	}
	
}
