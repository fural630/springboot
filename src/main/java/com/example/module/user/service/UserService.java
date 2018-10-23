package com.example.module.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.example.code.web.page.PageBean;
import com.example.code.web.page.RePage;
import com.example.module.user.dao.UserMapper;
import com.example.module.user.po.UserPO;
import com.github.pagehelper.PageHelper;
import com.sun.javafx.collections.MappingChange.Map;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public String getUser(String id) {
		UserPO userPO = userMapper.selectByPrimaryKey(id);
		return userPO.toString();
	}
	
	
	public RePage<UserPO> getUserManagePage(PageBean pageBean) {
		PageHelper.startPage(pageBean.getNowPage(), pageBean.getPageSize());
		List<UserPO> userPOList = userMapper.getUserPage();
		return new RePage<UserPO>(pageBean, userPOList);
	}
}
