package com.xhz.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import com.xhz.shiro.UserRealm;

@Configuration
@Profile({ "pro", "test", "dev" })
public class ShrioConfig {

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public Realm realm() {
		return new UserRealm();
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(defaultWebSecurityManager());
		shiroFilter.setLoginUrl("/login.html");
		// shiroFilter.setSuccessUrl("/index.html");
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		// 登录请求，允许访问
		filterChainDefinitionMap.put("/sys/login", "anon");
		// 静态资源匿名访问
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/font/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/plugins/**", "anon");
		// 退出请求，不需要配置action
		filterChainDefinitionMap.put("/logout", "logout");
		// 拦截其他所有请求，都需要登录允许
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}

	@Bean
	public DefaultWebSessionManager defaultWebSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		// 设置session过期时间为60分钟(单位：毫秒)，1分钟为60000，默认为30分钟
		sessionManager.setGlobalSessionTimeout(60000 * 60);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		return sessionManager;
	}

	@Bean
	public DefaultWebSecurityManager defaultWebSecurityManager() {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setSessionManager(defaultWebSessionManager());
		defaultWebSecurityManager.setRealm(realm());
		return defaultWebSecurityManager;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager());
		return authorizationAttributeSourceAdvisor;
	}
}
