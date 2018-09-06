package com.zm.zhuma.commons.web.annotations;

/**
 * @desc 日志打印
 *
 * @author zhumaer
 * @since 9/6/2018 3:00 PM
 */
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
