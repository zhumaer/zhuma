package com.zhuma.demo.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhuma.demo.interceptor.HeaderParamsCheckInterceptor;
import com.zhuma.demo.interceptor.LoginedAuthInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private LoginedAuthInterceptor loginedAuthInterceptor;

	@Autowired
	private HeaderParamsCheckInterceptor headerParamsCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String apiUri = "/**";
		//请求头参数校验
		registry.addInterceptor(headerParamsCheckInterceptor).addPathPatterns(apiUri);
		//登录拦截
		registry.addInterceptor(loginedAuthInterceptor).addPathPatterns(apiUri);
	}

}
