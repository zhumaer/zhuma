package com.zhuma.demo.comm.result;

/**
 * @desc 参数无效项
 * 
 * @author zhumaer
 * @since 10/9/2017 3:00 PM
 */
public class ParameterInvalidItem {

	/**
	 * 对象的名称
	 */
	private String objectName;

	/**
	 * 无效字段的名称
	 */
	private String fieldName;

	/**
	 * 错误信息
	 */
	private String message;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
