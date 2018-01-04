package com.zhuma.demo.exception;

import com.zhuma.demo.enums.ExceptionEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import com.zhuma.demo.enums.ResultCode;

/**
 * @desc 业务异常类
 * 
 * @author zhumaer
 * @since 9/18/2017 3:00 PM
 */
@Data
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 194906846739586856L;

	protected String code;

	protected String message;

	protected ResultCode resultCode;

	protected Object data;

	public BusinessException() {
		ExceptionEnum exceptionEnum = ExceptionEnum.getByEClass(this.getClass());
		if (exceptionEnum != null) {
			resultCode = exceptionEnum.getResultCode();
			code = exceptionEnum.getResultCode().code().toString();
			message = exceptionEnum.getResultCode().message();
		}

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

}
