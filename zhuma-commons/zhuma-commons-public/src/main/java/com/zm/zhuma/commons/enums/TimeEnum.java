package com.zm.zhuma.commons.enums;

/**
 * @desc 常用时间枚举类
 * 
 * @author zhumaer
 * @since 8/31/2017 3:00 PM
 */
public enum TimeEnum {

	/**
	 * 一分钟
	 */
	ONE_MINUTES(60),
	/**
	 * 一小时
	 */
	ONE_HOUR(60 * 60),
	/**
	 * 一天
	 */
	ONE_DAY(60 * 60 * 24),
	/**
	 * 一周
	 */
	ONE_WEEK(60 * 60 * 24 * 7),
	/**
	 * 一个月
	 */
	ONE_MONTH(60 * 60 * 24 * 30);

	private Integer sec;

	TimeEnum(Integer sec) {
		this.sec = sec;
	}

	public Integer sec() {
		return this.sec;
	}

	@Override
	public String toString() {
		return this.name();
	}
}
