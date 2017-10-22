package com.zhuma.demo.exceptions;

import org.apache.commons.lang3.StringUtils;

import com.zhuma.demo.enums.ResultCode;

/**
 * 业务异常类
 * Created by jingkun.wang on 17/5/24.
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 194906846739586856L;

	protected String code;

	protected String message;

	protected ResultCode resultCode;

	protected Object data;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		this.message = message;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String format, Object... objects) {
		format = StringUtils.replace(format, "{}", "%s");
		this.message = String.format(format, objects);
	}

	public BusinessException(String msg, Throwable cause, Object... objects) {
		String format = StringUtils.replace(msg, "{}", "%s");
		this.message= String.format(format, objects);
	}

	public BusinessException(ResultCode resultCode, Object data) {
		this(resultCode);
		this.data = data;
	}

	public BusinessException(ResultCode resultCode) {
		this.resultCode = resultCode;
		this.code = resultCode.code().toString();
		this.message = resultCode.message();
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public ResultCode getResultCode() {
		return this.resultCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
