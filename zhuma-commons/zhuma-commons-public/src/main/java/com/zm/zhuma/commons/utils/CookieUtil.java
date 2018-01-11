package com.zm.zhuma.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @desc cookie操作工具类
 * 
 * @author zhumaer
 * @since 6/20/2017 16:37 PM
 */
public class CookieUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtil.class);

	/**
	 * 添加cookie信息，不设置生效时间，默认浏览器关闭即失效 备注：默认开启HttpOnly
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {
		addCookie(response, name, value, -1, false, true, false);
	}

	/**
	 * 添加cookie信息，指定生效时间
	 * 
	 * secure属性
	 * 当设置为true时，表示创建的 Cookie 会被以安全的形式向服务器传输，也就是只能在 HTTPS 连接中被浏览器传递到服务器端进行会话验证，如果是 HTTP 连接则不会传递该信息，所以不会被窃取到Cookie 的具体内容。
	 * HttpOnly属性
	 * 如果在Cookie中设置了"HttpOnly"属性，那么通过程序(JS脚本、Applet等)将无法读取到Cookie信息，这样能有效的防止XSS攻击。
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, boolean isURLEncode, boolean isHttpOnly, boolean isSecure) {
		try {
			Cookie cookie = new Cookie(name, isURLEncode ? URLEncoder.encode(value, "UTF-8") : value);
			if (maxAge > 0) {
				cookie.setMaxAge(maxAge);
			}

			cookie.setPath("/");
			cookie.setHttpOnly(isHttpOnly);
			cookie.setSecure(isSecure);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("addCookie occurs exception, caused by: ", e);
		}
	}

	/**
	 * 删除cookie
	 */
	public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		if (StringUtil.isEmpty(name)) {
			return;
		}

		Cookie cookie = getCookie(request, name);
		if (null != cookie) {
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/**
	 * 获取cookie对象
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		if (StringUtil.isEmpty(name)) {
			return null;
		}

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}

		return null;
	}

	/**
	 * 获取登录的cookie值
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * 添加cookie信息，指定生效时间，是否URL编码 备注：默认开启HttpOnly
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, boolean isURLEncode) {
		if (StringUtil.isEmpty(value)) {
			return;
		}

		addCookie(response, name, value, maxAge, isURLEncode, true, false);
	}

	/**
	 * 获取登录的cookie值，是否进行解码
	 */
	public static String getCookieValue(HttpServletRequest request, String name, boolean isDecode) {
		if (StringUtil.isEmpty(name)) {
			return null;
		}

		try {
			Cookie cookie = getCookie(request, name);
			if (cookie != null) {
				return isDecode ? URLDecoder.decode(cookie.getValue(), "UTF-8") : cookie.getValue();
			}
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("getCookieValue occurs exception, caused by: ", e);
		}
		return null;
	}
}
