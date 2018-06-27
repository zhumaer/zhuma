package com.zm.zhuma.commons.lock.annotation;

import java.lang.annotation.*;

/**
 * @desc
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
@Target(value = {ElementType.PARAMETER, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface EasyLockKey {
    String value() default "";
}
