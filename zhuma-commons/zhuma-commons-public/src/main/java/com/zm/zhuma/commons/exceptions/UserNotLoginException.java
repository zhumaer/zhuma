package com.zm.zhuma.commons.exceptions;

/**
 * @desc 用户未登录异常
 * 
 * @author zhumaer
 * @since 9/18/2017 3:00 PM
 */
public class UserNotLoginException extends BusinessException {

	private static final long serialVersionUID = -1879503946782379204L;

	public UserNotLoginException() {
		super();
	}

	public UserNotLoginException(String msg) {
		super(msg);
	}

	public UserNotLoginException(String formatMsg, Object... objects) {
		super(formatMsg, objects);
	}

}
