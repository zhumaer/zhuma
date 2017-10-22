package com.zhuma.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhuma.demo.constant.HeaderConstants;
import com.zhuma.demo.enums.CallSource;
import com.zhuma.demo.enums.ResultCode;
import com.zhuma.demo.exception.BusinessException;
import com.zhuma.demo.util.StringUtil;

/**
 * @desc HEADER头参数校验
 * 
 * @author zhumaer
 * @since 8/29/2017 3:00 PM
 */
@Component
public class HeaderParamsCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			String callSource = request.getHeader(HeaderConstants.CALL_SOURCE);
			String apiVersion = request.getHeader(HeaderConstants.API_VERSION);
			String appVersion = request.getHeader(HeaderConstants.APP_VERSION);

			if (StringUtil.isAnyBlank(callSource, apiVersion)) {
				throw new BusinessException(ResultCode.PARAM_NOT_COMPLETE);
			}

			try {
				Double.valueOf(apiVersion);
			} catch (NumberFormatException e) {
				throw new BusinessException(ResultCode.PARAM_IS_INVALID);
			}

			if ((CallSource.ANDROID.name().equals(callSource) || CallSource.IOS.name().equals(callSource)) && StringUtil.isEmpty(appVersion)) {
				throw new BusinessException(ResultCode.PARAM_NOT_COMPLETE);
			}

			if (!CallSource.isValid(callSource)) {
				throw new BusinessException(ResultCode.PARAM_IS_INVALID);
			}

		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// nothing to do
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// nothing to do
	}

}
