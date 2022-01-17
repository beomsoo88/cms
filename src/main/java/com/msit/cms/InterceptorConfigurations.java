package com.msit.cms;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.msit.cms.util.interceptor.AdminLoginInterceptor;

@Configuration
public class InterceptorConfigurations implements WebMvcConfigurer {

	/**
	 * 인터셉터 등록 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AdminLoginInterceptor())
			.addPathPatterns("/mobile/**")
			.excludePathPatterns("/mobile/login")
			.excludePathPatterns("/mobile/login/**")
			.excludePathPatterns("/mobile/regist")
			.excludePathPatterns("/mobile/regist/**")
			.excludePathPatterns("/mobile/push/request*");
	}
	 
	/**
	 * TODO 경로 수정 예정
	 * root 경로로 접속 시 dashboard
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/dashboard");
	}
	
}
