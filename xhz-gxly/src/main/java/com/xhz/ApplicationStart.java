package com.xhz;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


@SpringBootApplication
@MapperScan("com.xhz")
public class ApplicationStart {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationStart.class);
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ApplicationStart.class, args);
		ConfigurableEnvironment env = context.getEnvironment();
		String[] activeProfiles = env.getActiveProfiles();
		logger.debug("系统已启动，当前启动的 profile 是 ：{} , port : {}", activeProfiles[0], env.getProperty("server.port"));
	} 
}
