package com.zhuma.demo.comm.result;

import java.io.Serializable;

import com.zhuma.demo.enums.ResultCode;

/**
 * Created by jingkun.wang on 17/5/24.
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -3948389268046368059L;

	private Integer code;

	private String msg;

	private Object data;

	public Result() {

	}

	public Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static Result success() {
		Result result = new Result();
		result.setResultCode(ResultCode.SUCCESS);
		return result;
	}

	public static Result success(Object data) {
		Result result = new Result();
		result.setResultCode(ResultCode.SUCCESS);
		result.setData(data);
		return result;
	}

	public static Result failure(ResultCode resultCode) {
		Result result = new Result();
		result.setResultCode(resultCode);
		return result;
	}

	public static Result failure(ResultCode resultCode, Object data) {
		Result result = new Result();
		result.setResultCode(resultCode);
		result.setData(data);
		return result;
	}

	public static Result failure(String message) {
		Result result = new Result();
		result.setCode(ResultCode.PARAM_IS_INVALID.code());
		result.setMsg(message);
		return result;
	}

	public void setResultCode(ResultCode code) {
		this.code = code.code();
		this.msg = code.message();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
