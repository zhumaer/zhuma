package com.zm.zhuma.commons.enums;

import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * @desc 环境常量枚举
 *
 * @author zhumaer
 * @since 8/31/2017 3:00 PM
 */
public enum EnvironmentEnum {
	/**
	 * 线上
	 */
	PROD,
	/**
	 * 联调
	 */
	FE,
	/**
	 * 测试
	 */
	QA;

	public static boolean isProdEnv(Environment env) {
		Assert.notNull(env, "env parameter not null.");

		return EnvironmentEnum.PROD.name().equalsIgnoreCase(env.getProperty("spring.profiles.active"));
	}

	@Override
	public String toString() {
		return this.name();
	}

}
