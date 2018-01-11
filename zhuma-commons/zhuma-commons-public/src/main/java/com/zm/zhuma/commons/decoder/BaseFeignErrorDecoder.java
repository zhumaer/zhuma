package com.zm.zhuma.commons.decoder;

import com.zm.zhuma.commons.exceptions.BusinessException;
import com.zm.zhuma.commons.result.DefaultErrorResult;
import com.zm.zhuma.commons.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import feign.Response;
import feign.RetryableException;

/**
 * @desc Feign错误解码器基础类
 * 
 * @author zhumaer
 * @since 10/10/2017 9:54 AM
 */
public class BaseFeignErrorDecoder extends feign.codec.ErrorDecoder.Default {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseFeignErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response) {

		Exception defaultResultE = super.decode(methodKey, response);

		if (RetryableException.class.equals(defaultResultE.getClass())) {
			return defaultResultE;
		}

		String message = defaultResultE.getMessage();
		String separator = "content:";
		String content = StringUtils.substringAfterLast(message, separator);
		DefaultErrorResult defaultResult = JSON.parseObject(content, DefaultErrorResult.class);
		if (defaultResult == null) {
			LOGGER.error("BaseFeignErrorDecoder occurs error, defaultResult is null, response:{}", JsonUtil.object2Json(response));
			return defaultResultE;
		}

		if (defaultResult.getStatus() != null && defaultResult.getCode() != null) {
			try {
				BusinessException businessException = (BusinessException) Class.forName(defaultResult.getException()).newInstance();
				businessException.setCode(defaultResult.getCode().toString());
				businessException.setMessage(defaultResult.getMessage());
				businessException.setData(defaultResult.getErrors());
				return businessException;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				LOGGER.error("business exception cast error, caused by: ", e);
			}
		}
		return defaultResultE;
	}

}
