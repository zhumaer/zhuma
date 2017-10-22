package com.zhuma.demo.constant;

import com.zhuma.demo.enums.CallSource;


/**
 * @desc Header的key罗列
 * 
 * @author zhumaer
 * @since 8/31/2017 3:00 PM
 */
public class HeaderConstants {

	/**
	 * 用户的登录token
	 */
	public static final String X_TOKEN = "X-Token";

	/**
	 * api的版本号
	 */
	public static final String API_VERSION = "Api-Version";

	/**
	 * app版本号
	 */
	public static final String APP_VERSION = "App-Version";

	/**
	 * 调用来源 {@link CallSource}
	 */
	public static final String CALL_SOURCE = "Call-Source";

}
