package com.zhuma.demo.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhuma.demo.annotations.LoginedAuth;
import com.zhuma.demo.enums.ResultCode;
import com.zhuma.demo.exceptions.BusinessException;
import com.zhuma.demo.helpers.LoginHelper;
import com.zhuma.demo.model.po.User;
import com.zhuma.demo.utils.StringUtil;

/**
 * @desc 已登录权限验证拦截器 备注：通过{@link LoginedAuth}配合使用
 * 
 * @author zhumaer
 * @since 6/20/2017 3:00 PM
 */
@Component
public class LoginedAuthInterceptor implements HandlerInterceptor {

	@Autowired
//	private UserLoginCacheService userLoginCacheService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			final HandlerMethod handlerMethod = (HandlerMethod) handler;
			final Class<?> clazz = handlerMethod.getBeanType();
			final Method method = handlerMethod.getMethod();
			if (clazz.isAnnotationPresent(LoginedAuth.class) || method.isAnnotationPresent(LoginedAuth.class)) {

				//直接获取登录用户（防止请求转发时，第二次查询）
				User loginedUser = LoginHelper.getLoginUserFromRequest(request);
				if (loginedUser != null) {
					return true;
				}

				//获取登录账号名
				String loginAccount = LoginHelper.getLoginAccount(request);
				if (StringUtil.isEmpty(loginAccount)) {
					throw new BusinessException(ResultCode.USER_NOT_LOGGED_IN);
				}

				//获取登录人信息
				User loginUser = null;
//				User loginUser = userLoginCacheService.getLoginUser(Long.valueOf(loginAccount));
				if (loginUser == null) {
					throw new BusinessException(ResultCode.USER_NOT_LOGGED_IN);
				}

				//登录用户信息放入请求对象，方便后续controller中获取
				LoginHelper.addLoginUserToRequest(request, loginUser);
				return true;
			}
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
