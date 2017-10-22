package com.zhuma.demo.exceptions;

/**
 * @desc 数据已经存在异常
 * 
 * @author jingkun.wang@baidao.com
 * @since 9/18/2017 3:00 PM
 */
public class DataConflictException extends BusinessException {

	private static final long serialVersionUID = 3721036867889297081L;

	public DataConflictException() {
		super();
	}

	public DataConflictException(String msg, Throwable cause, Object... objects) {
		super(msg, cause, objects);
	}

	public DataConflictException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DataConflictException(Throwable cause) {
		super(cause);
	}

	public DataConflictException(String msg) {
		super(msg);
	}

	public DataConflictException(String format, Object... objects) {
		super(format, objects);
	}

}
