package com.zhuma.demo.exceptions;

import org.apache.commons.lang3.StringUtils;

/**
 * @desc 远程访问异常
 * 
 * @author jingkun.wang@baidao.com
 * @since 7/18/2017 3:00 PM
 */
public class RemoteAccessException extends BusinessException {

	private static final long serialVersionUID = -832464574076215195L;

	private String message;

	public RemoteAccessException() {
		super();
	}
	public RemoteAccessException(String msg, Throwable cause, Object... objects) {
		super(cause);
		String format = StringUtils.replace(msg, "{}", "%s");
		this.message= String.format(format, objects);
	}

	public RemoteAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RemoteAccessException(Throwable cause) {
		super(cause);
	}

	public RemoteAccessException(String msg) {
		super(msg);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
