package com.zm.zhuma.commons.web.annotations;

import com.zm.zhuma.commons.web.result.PlatformResult;
import com.zm.zhuma.commons.web.result.Result;

import java.lang.annotation.*;

/**
 * @desc 接口返回结果增强  会通过拦截器拦截后放入标记，在WebResponseBodyHandler进行结果处理
 *
 * @author zhumaer
 * @since 4/1/2018 3:00 PM
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {

    Class<? extends Result> value() default PlatformResult.class;

}
