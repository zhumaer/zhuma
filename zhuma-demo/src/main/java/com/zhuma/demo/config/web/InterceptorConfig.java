package com.zhuma.demo.config.web;

import com.zm.zhuma.commons.enums.CacheKeyEnum;
import com.zm.zhuma.commons.web.interceptor.HeaderParamsCheckInterceptor;
import com.zm.zhuma.commons.web.interceptor.LoginAuthInterceptor;
import com.zm.zhuma.commons.web.interceptor.ResponseResultInterceptor;
import com.zm.zhuma.user.model.bo.LoginToken;
import com.zm.zhuma.user.token.service.LoginTokenService;
import com.zm.zhuma.user.token.service.impl.LoginTokenCacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private LoginAuthInterceptor loginAuthInterceptor;

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
		registry.addInterceptor(loginAuthInterceptor).addPathPatterns(apiUri);
	}

}
