package com.zhuma.demo.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zhuma.demo.comm.result.ParameterInvalidItem;

/**
 * @desc 类转化工具类
 * 
 * @author zhumaer
 * @since 6/20/2017 16:37 PM
 */
public class ConvertUtil {

	public static <T> List<ParameterInvalidItem> convertCVSetObjectToParameterInvalidItemList(Set<ConstraintViolation<T>> cvset) {
		if (CollectionUtils.isEmpty(cvset)) {
			return null;
		}

		List<ParameterInvalidItem> parameterInvalidItemList = Lists.newArrayList();
		for (ConstraintViolation<?> cv : cvset) {
			ParameterInvalidItem parameterInvalidItem = new ParameterInvalidItem();
			String propertyPath = cv.getPropertyPath().toString();
			if (propertyPath.indexOf(".") != -1) {
				String[] propertyPathArr = propertyPath.split("\\.");
				parameterInvalidItem.setFieldName(propertyPathArr[propertyPathArr.length - 1]);
			} else {
				parameterInvalidItem.setFieldName(propertyPath);
			}
			parameterInvalidItem.setMessage(cv.getMessage());
			parameterInvalidItemList.add(parameterInvalidItem);
		}

		return parameterInvalidItemList;
	}

	public static List<ParameterInvalidItem> convertCVSetToParameterInvalidItemList(Set<ConstraintViolation<?>> cvset) {
		if (CollectionUtils.isEmpty(cvset)) {
			return null;
		}

		List<ParameterInvalidItem> parameterInvalidItemList = Lists.newArrayList();
		for (ConstraintViolation<?> cv : cvset) {
			ParameterInvalidItem parameterInvalidItem = new ParameterInvalidItem();
			String propertyPath = cv.getPropertyPath().toString();
			if (propertyPath.indexOf(".") != -1) {
				String[] propertyPathArr = propertyPath.split("\\.");
				parameterInvalidItem.setFieldName(propertyPathArr[propertyPathArr.length - 1]);
			} else {
				parameterInvalidItem.setFieldName(propertyPath);
			}
			parameterInvalidItem.setMessage(cv.getMessage());
			parameterInvalidItemList.add(parameterInvalidItem);
		}

		return parameterInvalidItemList;
	}

	public static List<ParameterInvalidItem> convertBindingResultToMapParameterInvalidItemList(BindingResult bindingResult) {
		if (bindingResult == null) {
			return null;
		}

		List<ParameterInvalidItem> parameterInvalidItemList = Lists.newArrayList();

		List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrorList) {
			ParameterInvalidItem parameterInvalidItem = new ParameterInvalidItem();
//			parameterInvalidItem.setObjectName(fieldError.getObjectName());
			parameterInvalidItem.setFieldName(fieldError.getField());
			parameterInvalidItem.setMessage(fieldError.getDefaultMessage());
			parameterInvalidItemList.add(parameterInvalidItem);
		}

		return parameterInvalidItemList;
	}

	public static Map<String, Object> convertBeanToMap(Object object) {
		if (object == null) {
			return Collections.emptyMap();
		}
		return JSON.parseObject(JSON.toJSONString(object));
	}
}
