package com.zhuma.demo.comm.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @desc 平台通用返回结果
 * 
 * @author zhumaer
 * @since 10/9/2017 3:00 PM
 */
public class PlatformResult<T> implements Serializable {

	private static final long serialVersionUID = 7356370835188276633L;

	public static final String SUCCESS_RET = "0";

	public static final String SUCCESS = "Success";

	private String ret;

	private String msg;

	private T info;

	private String link;

	@JsonIgnore
	public boolean isSuccess() {
		return SUCCESS_RET.equals(ret);
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
