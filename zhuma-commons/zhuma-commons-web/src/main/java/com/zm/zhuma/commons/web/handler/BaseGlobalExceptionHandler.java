package com.zm.zhuma.commons.web.handler;

import com.zm.zhuma.commons.enums.ResultCode;
import com.zm.zhuma.commons.exceptions.BusinessException;
import com.zm.zhuma.commons.web.helper.ParameterInvalidItemHelper;
import com.zm.zhuma.commons.model.bo.ParameterInvalidItem;
import com.zm.zhuma.commons.web.result.DefaultErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @desc 全局异常处理基础类
 * 
 * @author zhumaer
 * @since 10/10/2017 9:54 AM
 */
@Slf4j
public abstract class BaseGlobalExceptionHandler {

	/**
	 * 违反约束异常
	 */
	protected DefaultErrorResult handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
		log.info("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
		List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.convertCVSetToParameterInvalidItemList(e.getConstraintViolations());
		return DefaultErrorResult.failure(ResultCode.PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvalidItemList);
	}

	/**
	 * 处理验证参数封装错误时异常
	 */
	protected DefaultErrorResult handleConstraintViolationException(HttpMessageNotReadableException e, HttpServletRequest request) {
		log.info("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
		return DefaultErrorResult.failure(ResultCode.PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 处理参数绑定时异常（反400错误码）
	 */
	protected DefaultErrorResult handleBindException(BindException e, HttpServletRequest request) {
		log.info("handleBindException start, uri:{}, caused by: ", request.getRequestURI(), e);
		List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.convertBindingResultToMapParameterInvalidItemList(e.getBindingResult());
		return DefaultErrorResult.failure(ResultCode.PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvalidItemList);
	}

	/**
	 * 处理使用@Validated注解时，参数验证错误异常（反400错误码）
	 */
	protected DefaultErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		log.info("handleMethodArgumentNotValidException start, uri:{}, caused by: ", request.getRequestURI(), e);
		List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.convertBindingResultToMapParameterInvalidItemList(e.getBindingResult());
		return DefaultErrorResult.failure(ResultCode.PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvalidItemList);
	}

	/**
	 * 处理通用自定义业务异常
	 */
	protected ResponseEntity<DefaultErrorResult> handleBusinessException(BusinessException e, HttpServletRequest request) {
		log.info("handleBusinessException start, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());

		DefaultErrorResult defaultErrorResult = DefaultErrorResult.failure(e);
		return ResponseEntity
				.status(HttpStatus.valueOf(defaultErrorResult.getStatus()))
				.body(defaultErrorResult);
	}

	/**
	 * 处理未预测到的其他错误（反500错误码）
	 */
	protected DefaultErrorResult handleThrowable(Throwable e, HttpServletRequest request) {
		log.error("handleThrowable start, uri:{}, caused by: ", request.getRequestURI(), e);
		return DefaultErrorResult.failure(ResultCode.SYSTEM_INNER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
