package com.zhuma.demo.exception;

/**
 * @desc 数据没有找到异常
 * 
 * @author zhumaer
 * @since 9/18/2017 3:00 PM
 */
public class DataNotFoundException extends BusinessException {

	private static final long serialVersionUID = 3721036867889297081L;

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String msg, Throwable cause, Object... objects) {
		super(msg, cause, objects);
	}

	public DataNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

	public DataNotFoundException(String msg) {
		super(msg);
	}

	public DataNotFoundException(String format, Object... objects) {
		super(format, objects);
	}

}
