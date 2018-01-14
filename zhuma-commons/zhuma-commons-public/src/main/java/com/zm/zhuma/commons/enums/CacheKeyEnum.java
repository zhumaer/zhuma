package com.zm.zhuma.commons.enums;

import com.zm.zhuma.commons.utils.StringUtil;

/**
 * 统一定义缓存KEY
 * 备注： ①枚举name应遵守以VALUE、LIST、SET、ZSET、HASH等开头
 *      ②枚举code应尽量简写形式，以工程主名字为开头，例如：zhuma、zhumm_push
 *      ③ `:` 冒号分割的前后应该是一个名词词性，冒号前后是有上下级关系的，多个单词解释一个名词时，约定使用 `_`下划线分割
 * 
 * 举例： ①DX-IA的用户缓存 zhuma:user:{userId}
 *      ②DX-IA的某用户地址信息缓存 ia:user_address
 * 
 * Created by zhumaer on 17/5/24.
 */
public enum CacheKeyEnum {

	/* ---------------用户相关缓存------------------ */
	/**
	 * 登录TOKEN缓存key
	 */
	VALUE_LOGIN_TOKENS("asst:login_tokens:%s", TimeEnum.ONE_WEEK.sec()),

	/**
	 * 用户缓存
	 */
	VALUE_USERS("asst:user:profile:%s", TimeEnum.ONE_WEEK.sec());

	/**
	 * 缓存key
	 */
	private String code;

	/**
	 * 过期时间（单位：秒）
	 */
	private Integer sec;

	CacheKeyEnum(String code, Integer sec) {
		this.code = code;
		this.sec = sec;
	}

	public String code() {
		return this.code;
	}

	public Integer sec() {
		return this.sec;
	}

	@Override
	public String toString() {
		return this.name();
	}

	public String formatKey(Object... args) {
		int requiredNum = StringUtil.getSubStrCount(this.code, "%s");
		if (requiredNum != 0 && (args == null || args.length != requiredNum)) {
			throw new IllegalArgumentException("The number of parameters is not equal to the required number.");
		}
		return String.format(this.code, args);
	}
}
