package com.xhz.web.module.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.web.module.sys.entity.UserDO;

@Service
public class TestService {
	
	@Autowired
	private UserService userService;
	
	public void test() {
		userService.insert(new UserDO());
//		throw new RuntimeException("错误222");
	}
}
