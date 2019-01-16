package com.xhz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Swagger API 配置页面
 * @author zhangzm
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnabled;
	
	@Value(value = "${swagger.title}")
	String swaggerTitle;
	
	@Value(value = "${swagger.scan.package}")
	String swaggerScanPackage;
	
	@Value(value = "${swagger.description}")
	String swaggerDescription;
	
	@Value(value = "${swagger.version}")
	String swaggerVersion;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				// 是否开启
				.enable(swaggerEnabled).select()
				// 扫描的路径包
				.apis(RequestHandlerSelectors.basePackage(swaggerScanPackage))
				// 指定路径处理PathSelectors.any()代表所有的路径
				.build().pathMapping("/");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(swaggerTitle).description(swaggerDescription)
				.version(swaggerVersion).build();
	}

}
