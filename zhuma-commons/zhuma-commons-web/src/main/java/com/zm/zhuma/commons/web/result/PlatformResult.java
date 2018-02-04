package com.zm.zhuma.commons.web.result;

import com.zm.zhuma.commons.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc 平台通用返回结果
 * 
 * @author zhumaer
 * @since 10/9/2017 3:00 PM
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlatformResult implements Result {

	private static final long serialVersionUID = 874200365941306385L;

	private Integer code;

	private String msg;

	private Object data;

	public static PlatformResult success() {
		PlatformResult result = new PlatformResult();
		result.setResultCode(ResultCode.SUCCESS);
		return result;
	}

	public static PlatformResult success(Object data) {
		PlatformResult result = new PlatformResult();
		result.setResultCode(ResultCode.SUCCESS);
		result.setData(data);
		return result;
	}

	public static PlatformResult failure(ResultCode resultCode) {
		PlatformResult result = new PlatformResult();
		result.setResultCode(resultCode);
		return result;
	}

	public static PlatformResult failure(ResultCode resultCode, Object data) {
		PlatformResult result = new PlatformResult();
		result.setResultCode(resultCode);
		result.setData(data);
		return result;
	}

	public static PlatformResult failure(String message) {
		PlatformResult result = new PlatformResult();
		result.setCode(ResultCode.PARAM_IS_INVALID.code());
		result.setMsg(message);
		return result;
	}

	private void setResultCode(ResultCode code) {
		this.code = code.code();
		this.msg = code.message();
	}

}
