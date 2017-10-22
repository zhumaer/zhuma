package com.zhuma.demo.exceptions;

import com.zhuma.demo.enums.ResultCode;


/**
 * @desc 参数无效异常
 * 
 * @author jingkun.wang@baidao.com
 * @since 9/18/2017 3:00 PM
 */
public class ParameterInvalidException extends BusinessException {

	private static final long serialVersionUID = 3721036867889297081L;

	public ParameterInvalidException() {
		super();
	}

	public ParameterInvalidException(Object data) {
		super.data = data;
	}

	public ParameterInvalidException(ResultCode resultCode, Object data) {
		super.resultCode = resultCode;
		super.data = data;
	}

	public ParameterInvalidException(String msg, Throwable cause, Object... objects) {
		super(msg, cause, objects);
	}

	public ParameterInvalidException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ParameterInvalidException(Throwable cause) {
		super(cause);
	}

	public ParameterInvalidException(String msg) {
		super(msg);
	}

	public ParameterInvalidException(String format, Object... objects) {
		super(format, objects);
	}

}
