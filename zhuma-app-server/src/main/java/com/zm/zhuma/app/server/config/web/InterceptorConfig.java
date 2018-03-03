package com.zm.zhuma.app.server.config.web;

import com.zm.zhuma.commons.web.interceptor.AllowCrossDomainInterceptor;
import com.zm.zhuma.commons.web.interceptor.HeaderParamsCheckInterceptor;
import com.zm.zhuma.commons.web.interceptor.LoginAuthInterceptor;
import com.zm.zhuma.commons.web.interceptor.ResponseResultInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private AllowCrossDomainInterceptor allowCrossDomainInterceptor;

	@Autowired
	private LoginAuthInterceptor loginAuthInterceptor;

	@Autowired
	private HeaderParamsCheckInterceptor headerParamsCheckInterceptor;

	@Autowired
	private ResponseResultInterceptor responseResultInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String apiUri = "/**";

		//允许跨域
		registry.addInterceptor(allowCrossDomainInterceptor).addPathPatterns(apiUri);
		//请求头参数校验
		registry.addInterceptor(headerParamsCheckInterceptor).addPathPatterns(apiUri);
		//登录拦截
		registry.addInterceptor(loginAuthInterceptor).addPathPatterns(apiUri);
		//响应结果控制拦截
		registry.addInterceptor(responseResultInterceptor).addPathPatterns(apiUri);
	}

	@Bean
	public AllowCrossDomainInterceptor allowCrossDomainInterceptor() {
		return new AllowCrossDomainInterceptor();
	}

	@Bean
	public LoginAuthInterceptor loginAuthInterceptor() {
		return new LoginAuthInterceptor();
	}

	@Bean
	public HeaderParamsCheckInterceptor headerParamsCheckInterceptor() {
		return new HeaderParamsCheckInterceptor();
	}

	@Bean
	public ResponseResultInterceptor responseResultInterceptor() {
		return new ResponseResultInterceptor();
	}

}
