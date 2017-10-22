package com.zhuma.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhuma.demo.constants.Constants;
import com.zhuma.demo.interceptors.LoginedAuthInterceptor;

/**
 * @desc WEB配置类
 * 
 * @author jingkun.wang@baidao.com
 * @since 6/23/2017 14:11 PM
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private LoginedAuthInterceptor loginedAuthInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String apiUri = "/" + Constants.API_ROOT + "/**";
		//登录拦截
		registry.addInterceptor(loginedAuthInterceptor).addPathPatterns(apiUri);
	}

}
