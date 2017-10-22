package com.zhuma.demo.comm.result;

import java.util.Date;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;

import com.zhuma.demo.enums.ResultCode;

/**
 * @desc 默认全局错误返回结果
 *       备注：该返回信息是spring boot的默认异常时返回结果{@link DefaultErrorAttributes}，目前也是我们原子层服务的默认返回结果
 * 
 * @author zhumaer
 * @since 9/29/2017 3:00 PM
 */
public class DefaultResult {

	/**
	 * HTTP响应状态码 {@link org.springframework.http.HttpStatus}
	 */
	private Integer status;

	/**
	 * HTTP响应状态码的英文提示
	 */
	private String error;

	/**
	 * 异常堆栈的精简信息
	 * 
	 */
	private String message;

	/**
	 * 我们系统内部自定义的返回值编码，{@link ResultCode} 它是对错误更加详细的编码
	 * 
	 * 备注：spring boot默认返回异常时，该字段为null
	 */
	private String code;

	/**
	 * 调用接口路径
	 */
	private String path;

	/**
	 * 异常的名字
	 */
	private String exception;

	/**
	 * 异常的错误传递的数据
	 */
	private Object errors;

	/**
	 * 时间戳
	 */
	private Date timestamp;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
