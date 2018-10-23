package com.example.module.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.code.web.page.PageBean;
import com.example.code.web.page.RePage;
import com.example.module.user.po.UserPO;
import com.example.module.user.service.UserService;
import com.example.util.Dumper;
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
	public RePage<UserPO> getUserTable (PageBean pageBean) {
		RePage<UserPO> rePage = userService.getUserManagePage(pageBean);
		Dumper.dump(rePage);
		return rePage;
	}
	
}
