package com.zm.zhuma.commons.lock.annotation;

import com.zm.zhuma.commons.lock.model.LockType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @desc 分布式锁
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyLock {

    /**
     * 锁的名称
     */
    String name() default "";

    /**
     * 自定义业务key
     */
    String [] keys() default {};

    /**
     * 锁类型
     */
    LockType lockType() default LockType.Reentrant;

    /**
     * 最多等待时间（默认单位：秒）
     */
    long waitTime() default 30;

    /**
     * 自动解锁时间（默认单位：秒）
     */
    long leaseTime() default -1;

    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
