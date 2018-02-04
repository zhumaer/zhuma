package com.zm.zhuma.commons.web.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zm.zhuma.commons.utils.IpUtil;
import com.zm.zhuma.commons.web.constants.HeaderConstants;
import com.zm.zhuma.commons.web.handler.GlobalExceptionHandler;
import com.zm.zhuma.user.model.bo.LoginUser;
import com.zm.zhuma.user.token.helper.LoginTokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @desc 请求参数、响应体统一日志打印
 * 
 * @author zhumaer
 * @since 10/10/2017 9:54 AM
 */
@Slf4j
@Aspect
@Component
public class RestControllerAspect {

	/**
	 * 环绕通知
	 * @param joinPoint 连接点
	 * @return 切入点返回值
	 * @throws Throwable 异常信息
	 */
	@Around("@within(org.springframework.web.bind.annotation.RestController) || @annotation(org.springframework.web.bind.annotation.RestController)")
	public Object apiLog(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		boolean logFlag = this.needToLog(method);
		if (!logFlag) {
			return joinPoint.proceed();
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();

		String ip = IpUtil.getRealIp(request);
		String methodName = this.getMethodName(joinPoint);
		String params = this.getParamsJson(joinPoint);
		String requester = loginUser == null ? "unknown" : String.valueOf(loginUser.getId());

		String callSource = request.getHeader(HeaderConstants.CALL_SOURCE);
		String appVersion = request.getHeader(HeaderConstants.APP_VERSION);
		String apiVersion = request.getHeader(HeaderConstants.API_VERSION);
		String userAgent = request.getHeader("user-agent");

		log.info("Started request requester [{}] method [{}] params [{}] IP [{}] callSource [{}] appVersion [{}] apiVersion [{}] userAgent [{}]", requester, methodName, params, ip, callSource, appVersion, apiVersion, userAgent);
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		log.info("Ended request requester [{}] method [{}] params[{}] response is [{}] cost [{}] millis ",
				requester, methodName, params, this.deleteSensitiveContent(result), System.currentTimeMillis() - start);
		return result;
	}

	private String getMethodName(ProceedingJoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toShortString();
		String SHORT_METHOD_NAME_SUFFIX = "(..)";
		if (methodName.endsWith(SHORT_METHOD_NAME_SUFFIX)) {
			methodName = methodName.substring(0, methodName.length() - SHORT_METHOD_NAME_SUFFIX.length());
		}
		return methodName;
	}

	private String getParamsJson(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		StringBuilder sb = new StringBuilder();
		for (Object arg : args) {
			//移除敏感内容
			String paramStr;
			if (arg instanceof HttpServletResponse) {
				paramStr = HttpServletResponse.class.getSimpleName();
			} else if (arg instanceof HttpServletRequest) {
				paramStr = HttpServletRequest.class.getSimpleName();
			} else if (arg instanceof MultipartFile) {
				long size = ((MultipartFile) arg).getSize();
				paramStr = MultipartFile.class.getSimpleName() + " size:" + size;
			} else {
				paramStr = this.deleteSensitiveContent(arg);
			}
			sb.append(paramStr).append(",");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}

	private boolean needToLog(Method method) {
		//GET请求不记录日志
		return method.getAnnotation(GetMapping.class) == null
				&& !method.getDeclaringClass().equals(GlobalExceptionHandler.class);
	}

	/**
	 * 删除参数中的敏感内容
	 * @param obj 参数对象
	 * @return 去除敏感内容后的参数对象
	 */
	private String deleteSensitiveContent(Object obj) {
		JSONObject jsonObject = new JSONObject();
		if (obj == null || obj instanceof Exception) {
			return jsonObject.toJSONString();
		}

		try {
			String param = JSON.toJSONString(obj);
			jsonObject = JSONObject.parseObject(param);
			List<String> sensitiveFieldList = this.getSensitiveFieldList();
			for (String sensitiveField : sensitiveFieldList) {
				if (jsonObject.containsKey(sensitiveField)) {
					jsonObject.put(sensitiveField, "******");
				}
			}
		} catch (ClassCastException e) {
			return String.valueOf(obj);
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 敏感字段列表
	 */
	private List<String> getSensitiveFieldList() {
		List<String> sensitiveFieldList = Lists.newArrayList();
		sensitiveFieldList.add("pwd");
		sensitiveFieldList.add("password");
		return sensitiveFieldList;
	}
}
