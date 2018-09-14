package com.zm.zhuma.commons.web.resolver;

import com.zm.zhuma.commons.annotations.LoginAuth;
import com.zm.zhuma.commons.util.BeanFieldUtil;
import com.zm.zhuma.commons.util.annotations.Convert;
import com.zm.zhuma.user.model.bo.LoginUser;
import com.zm.zhuma.user.token.helper.LoginTokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;

/**
 * @desc 登录用户参数解析器
 *
 * @author zhumaer
 * @since 3/5/2017 3:00 PM
 */
@Slf4j
public class ArgumentConvertResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Convert convert = parameter.getParameterAnnotation(Convert.class);
		return convert != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return null;
	}
}
