package com.example.module.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@RequestMapping("list")
	public String list() {
		return "login/list";
	}
}
