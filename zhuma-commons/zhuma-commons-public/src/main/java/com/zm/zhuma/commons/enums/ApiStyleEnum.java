package com.zm.zhuma.commons.enums;

/**
 * @desc 接口返回值风格样式枚举类
 * 
 * @author zhumaer
 * @since 8/31/2017 3:00 PM
 */
public enum ApiStyleEnum {
	NONE;

	public static boolean isValid(String name) {
		for (ApiStyleEnum callSource : ApiStyleEnum.values()) {
			if (callSource.name().equals(name)) {
				return true;
			}
		}
		return false;
	}

}
