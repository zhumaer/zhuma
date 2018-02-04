package com.zhuma.demo.config.web;

import com.zhuma.demo.interceptor.ResponseResultInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhuma.demo.interceptor.HeaderParamsCheckInterceptor;
import com.zhuma.demo.interceptor.LoginAuthInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private LoginAuthInterceptor LoginAuthInterceptor;

	@Autowired
	private HeaderParamsCheckInterceptor headerParamsCheckInterceptor;

	@Autowired
	private ResponseResultInterceptor responseResultInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String apiUri = "/**";
		//响应结果控制拦截
		registry.addInterceptor(responseResultInterceptor).addPathPatterns(apiUri);
		//请求头参数校验
//		registry.addInterceptor(headerParamsCheckInterceptor).addPathPatterns(apiUri);
		//登录拦截
		registry.addInterceptor(LoginAuthInterceptor).addPathPatterns(apiUri);
	}

}
