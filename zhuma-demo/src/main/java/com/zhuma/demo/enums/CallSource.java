package com.zhuma.demo.enums;

/**
 * @desc 调用来源枚举类
 * 
 * @author jingkun.wang@baidao.com
 * @since 8/31/2017 3:00 PM
 */
public enum CallSource {
	WEB,
	PC,
	WECHAT,
	IOS,
	ANDROID;

	public static boolean isValid(String name) {
		for (CallSource callSource : CallSource.values()) {
			if (callSource.name().equals(name)) {
				return true;
			}
		}
		return false;
	}

}
