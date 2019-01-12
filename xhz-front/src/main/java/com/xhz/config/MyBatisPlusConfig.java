package com.xhz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;

/**
 * mybatis plus 逻辑删除插件
 * @author zhangzm
 *
 */
@Configuration
public class MyBatisPlusConfig {
	
	@Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
