package com.xhz.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
/**
 * 404页面统一跳转
 * @author zhangzm
 *
 */
@Configuration
public class ErrorPageConfig {
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
		return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
			@Override
			public void customize(ConfigurableServletWebServerFactory factory) {
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/common/404.html");
				factory.addErrorPages(error404Page);
			}
		};
	}
}
