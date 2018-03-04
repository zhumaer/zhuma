package com.zhuma.demo.config.web;

import com.zm.zhuma.commons.web.interceptor.HeaderParamsCheckInterceptor;
import com.zm.zhuma.commons.web.interceptor.LoginAuthInterceptor;
import com.zm.zhuma.commons.web.interceptor.ResponseResultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Bean
	public LoginAuthInterceptor loginAuthInterceptor() {
		return new LoginAuthInterceptor();
	}

	@Bean
	public ResponseResultInterceptor responseResultInterceptor() {
		return new ResponseResultInterceptor();
	}

	@Bean
	public HeaderParamsCheckInterceptor headerParamsCheckInterceptor() {
		return new HeaderParamsCheckInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String apiUri = "/**";
		//响应结果控制拦截
		registry.addInterceptor(responseResultInterceptor());
		//请求头参数校验
//		registry.addInterceptor(headerParamsCheckInterceptor).addPathPatterns(apiUri);
		//登录拦截
		registry.addInterceptor(loginAuthInterceptor());
	}

}
