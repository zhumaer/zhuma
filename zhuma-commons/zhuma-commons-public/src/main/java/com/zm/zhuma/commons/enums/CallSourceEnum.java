package com.zm.zhuma.commons.enums;

/**
 * @desc 调用来源枚举类
 * 
 * @author zhumaer
 * @since 8/31/2017 3:00 PM
 */
public enum CallSourceEnum {
	/**
	 * WEB网站
	 */
	WEB,
	/**
	 * PC客户端
	 */
	PC,
	/**
	 * 微信公众号
	 */
	WECHAT,
	/**
	 * IOS平台
	 **/
	IOS,
	/**
	 * 安卓平台
	 */
	ANDROID;

	public static boolean isValid(String name) {
		for (CallSourceEnum callSource : CallSourceEnum.values()) {
			if (callSource.name().equals(name)) {
				return true;
			}
		}
		return false;
	}

}
