package com.xhz.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ShrioConfig {

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public Realm realm() {
		TextConfigurationRealm realm = new TextConfigurationRealm();
		realm.setUserDefinitions("joe.coder=password,user\n" + "admin=123,admin");

		realm.setRoleDefinitions("admin=read,write\n" + "user=read");
		realm.setCachingEnabled(true);
		return realm;
	}


	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		// 登录请求，允许访问
		chainDefinition.addPathDefinition("/sys/login", "anon");
		// 静态资源匿名访问
		chainDefinition.addPathDefinition("/css/**", "anon");
		chainDefinition.addPathDefinition("/font/**", "anon");
		chainDefinition.addPathDefinition("/img/**", "anon");
		chainDefinition.addPathDefinition("/js/**", "anon");
		chainDefinition.addPathDefinition("/plugins/**", "anon");
		// 退出请求，不需要配置action
		chainDefinition.addPathDefinition("/logout", "logout");
		// 拦截其他所有请求，都需要登录允许
		chainDefinition.addPathDefinition("/**", "authc");
		return chainDefinition;
	}

}
