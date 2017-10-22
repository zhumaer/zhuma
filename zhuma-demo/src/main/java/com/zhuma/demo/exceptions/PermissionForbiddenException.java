package com.zhuma.demo.exceptions;

/**
 * @desc 权限不足异常
 * 
 * @author jingkun.wang@baidao.com
 * @since 9/18/2017 3:00 PM
 */
public class PermissionForbiddenException extends BusinessException {

	private static final long serialVersionUID = 3721036867889297081L;

	public PermissionForbiddenException() {
		super();
	}

	public PermissionForbiddenException(String msg, Throwable cause, Object... objects) {
		super(msg, cause, objects);
	}

	public PermissionForbiddenException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PermissionForbiddenException(Throwable cause) {
		super(cause);
	}

	public PermissionForbiddenException(String msg) {
		super(msg);
	}

	public PermissionForbiddenException(String format, Object... objects) {
		super(format, objects);
	}

}
