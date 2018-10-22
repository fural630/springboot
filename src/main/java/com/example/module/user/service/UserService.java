package com.example.module.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.module.user.dao.UserMapper;
import com.example.module.user.po.UserPO;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public String getUser(String id) {
		UserPO userPO = userMapper.selectByPrimaryKey(id);
		return userPO.toString();
	}
}
