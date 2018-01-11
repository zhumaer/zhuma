package com.zm.zhuma.app.server.helper;


import com.zm.zhuma.commons.annotations.LoginAuth;
import com.zm.zhuma.commons.enums.TimeEnum;
import com.zm.zhuma.commons.utils.AESUtil;
import com.zm.zhuma.commons.utils.CookieUtil;
import com.zm.zhuma.commons.utils.StringUtil;
import com.zm.zhuma.user.po.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @desc 用户登录辅助类
 * 
 * @author zhumaer
 * @since 6/20/2017 18:31 PM
 */
public class LoginHelper {

	private final static String SECRET_KEY = "q8s55w1s8xhn4dyy";

	private final static String USER_TOKEN_NAME = "tk";

	private final static int LOGIN_MAX_AGE = TimeEnum.ONE_WEEK.sec();

	private final static String LOGIN_USER_KEY = "LOGIN_USER";
	
	/**
	 * 添加登录账号信息到COOKIE中
	 */
	public static void addLoginAccountToCookie(HttpServletResponse response, String loginAcount, Boolean isRemember) {
		CookieUtil.addCookie(response, USER_TOKEN_NAME, encodeLoginToken(loginAcount), isRemember != null && isRemember ? LOGIN_MAX_AGE : -1, true);
	}

	/**
	 * 获取登录账号信息到COOKIE中
	 */
	public static String getLoginAccountFromCookie(HttpServletRequest request) {
		String encodeLoginToken = CookieUtil.getCookieValue(request, USER_TOKEN_NAME, true);
		if (StringUtil.isEmpty(encodeLoginToken)) {
			return null;
		}

		return decodeLoginToken(encodeLoginToken);
	}

	/**
	 * 清理登录账号信息从COOKIE中
	 */
	public static void delLoginAccountFromCookie(HttpServletRequest request, HttpServletResponse response) {
		CookieUtil.delCookie(request, response, USER_TOKEN_NAME);
	}

	/**
	 * 获取登录账号信息从请求对象中
	 */
	public static String getLoginAccount(HttpServletRequest request) {
		String loginAccount = request.getHeader(USER_TOKEN_NAME);
		if (StringUtil.isNotEmpty(loginAccount)) {
			return decodeLoginToken(loginAccount);
		}

		loginAccount = getLoginAccountFromCookie(request);
		if (StringUtil.isNotEmpty(loginAccount)) {
			return loginAccount;
		}

		return null;
	}

	public static String encodeLoginToken(String loginAccount) {
		if (StringUtil.isEmpty(loginAccount)) {
			return null;
		}

		return AESUtil.encrypt(loginAccount, SECRET_KEY);
	}

	public static String decodeLoginToken(String loginToken) {
		if (StringUtil.isEmpty(loginToken)) {
			return null;
		}

		return AESUtil.decrypt(loginToken, SECRET_KEY);
	}

	/**
	 * 将用户信息放入请求对象
	 */
	public static void addLoginUserToRequest(HttpServletRequest request, User user) {
		request.setAttribute(LOGIN_USER_KEY, user);
	}

	/**
	 * 获取登录用户信息从请求对象 备注：使用该方法时需要在对应controller类或方法上加{@link LoginAuth}}注解
	 */
	public static User getLoginUserFromRequest(HttpServletRequest request) {
		Object userO = request.getAttribute(LOGIN_USER_KEY);
		if (userO == null) {
			return null;
		}

		return (User)userO;
	}
}
