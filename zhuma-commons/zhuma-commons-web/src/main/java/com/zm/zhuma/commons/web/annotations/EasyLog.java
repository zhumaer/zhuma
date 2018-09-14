package com.zm.zhuma.commons.web.annotations;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurationSelector;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @desc 日志打印
 *
 * @author zhumaer
 * @since 9/6/2018 3:00 PM
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AsyncConfigurationSelector.class})
public @interface EasyLog {

    /**
     * 是否关闭日志输出功能
     */
    boolean enable() default true;

    /**
     * 是否输出执行时间
     */
    boolean duration() default true;

    /**
     * 是否只在debug输出日志
     */
    boolean response() default true;
}
