package com.zhuma.demo.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.zhuma.demo.comm.result.Result;
import com.zhuma.demo.util.ConvertUtil;

/**
 * @desc 接口响应体处理器
 * 
 * @author zhumaer
 * @since 8/31/2017 3:00 PM
 */
@ControllerAdvice
public class WebResponseBodyHandler implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		Boolean isPlatformResult = (Boolean)servletRequest.getAttribute("IS_PLATFORM_RESULT");

		if (body instanceof Result) {
			Result result = (Result)body;
			if (Boolean.TRUE.equals(isPlatformResult)) {
				return ConvertUtil.convertResultToPlatformResult(result);
			}
		}
		return body;
	}

}
