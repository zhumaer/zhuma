package com.zhuma.demo.comm.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 平台通用返回结果（不同JAVA 团队、C++端对统一接口返回值）
 * Created by jingkun.wang on 17/6/14.
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
