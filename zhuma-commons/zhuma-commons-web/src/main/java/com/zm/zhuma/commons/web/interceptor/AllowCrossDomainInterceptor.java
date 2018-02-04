package com.zm.zhuma.commons.web.interceptor;

import com.zm.zhuma.commons.enums.EnvironmentEnum;
import com.zm.zhuma.commons.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @desc 允许跨域拦截器
 *
 * @author zhumaer
 * @since 6/20/2017 3:00 PM
 */
public class AllowCrossDomainInterceptor implements HandlerInterceptor {

	@Autowired
	private Environment env;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (!EnvironmentEnum.isProdEnv(env)) {
			String origin = request.getHeader("Origin");
			response.setHeader("Access-Control-Allow-Origin", StringUtil.isEmpty(origin) ? "*" : origin);
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
			response.setHeader("Access-Control-Max-Age", "0");
			response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("XDomainRequestAllowed", "1");
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// nothing to do
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// nothing to do
	}

}
