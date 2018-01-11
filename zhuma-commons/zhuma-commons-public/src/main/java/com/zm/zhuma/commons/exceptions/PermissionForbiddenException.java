package com.zm.zhuma.commons.exceptions;


import com.zm.zhuma.commons.enums.ResultCode;

/**
 * @desc 权限不足异常
 * 
 * @author zhumaer
 * @since 9/18/2017 3:00 PM
 */
public class PermissionForbiddenException extends BusinessException {

	private static final long serialVersionUID = 3721036867889297081L;

	public PermissionForbiddenException() {
		super();
	}

	public PermissionForbiddenException(Object data) {
		super.data = data;
	}

	public PermissionForbiddenException(ResultCode resultCode) {
		super(resultCode);
	}

	public PermissionForbiddenException(ResultCode resultCode, Object data) {
		super(resultCode, data);
	}

	public PermissionForbiddenException(String msg) {
		super(msg);
	}

	public PermissionForbiddenException(String formatMsg, Object... objects) {
		super(formatMsg, objects);
	}

}
