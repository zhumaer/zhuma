package com.zm.zhuma.commons.enums;

/**
 * @desc 调用来源枚举类
 * 
 * @author zhumaer
 * @since 8/31/2017 3:00 PM
 */
public enum CallSourceEnum {
	WEB,
	PC,
	WECHAT,
	IOS,
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
