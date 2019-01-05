package com.xhz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnabled;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				// 是否开启
				.enable(swaggerEnabled).select()
				// 扫描的路径包
				.apis(RequestHandlerSelectors.basePackage("com.frame.web.module"))
				// 指定路径处理PathSelectors.any()代表所有的路径
				.build().pathMapping("/");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SpringBoot-Swagger2集成和使用-demo示例").description("springboot | swagger")
				// 作者信息
				.contact(new Contact("一只袜子", null, null))
				.version("1.0.0").build();
	}

}
