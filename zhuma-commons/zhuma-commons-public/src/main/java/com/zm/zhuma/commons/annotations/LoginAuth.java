package com.zm.zhuma.commons.annotations;

import java.lang.annotation.*;

/**
 * @desc 已登录权限验证注解
 * 
 * @author zhumaer
 * @since 10/17/2017 3:13 PM
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginAuth {

}
