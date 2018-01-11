package com.zm.zhuma.commons.exceptions;


import com.zm.zhuma.commons.enums.ResultCode;

/**
 * @desc 数据已经存在异常
 * 
 * @author zhumaer
 * @since 9/18/2017 3:00 PM
 */
public class DataConflictException extends BusinessException {

	private static final long serialVersionUID = 3721036867889297081L;

	public DataConflictException() {
		super();
	}

	public DataConflictException(Object data) {
		super.data = data;
	}

	public DataConflictException(ResultCode resultCode) {
		super(resultCode);
	}

	public DataConflictException(ResultCode resultCode, Object data) {
		super(resultCode, data);
	}

	public DataConflictException(String msg) {
		super(msg);
	}

	public DataConflictException(String formatMsg, Object... objects) {
		super(formatMsg, objects);
	}


}
