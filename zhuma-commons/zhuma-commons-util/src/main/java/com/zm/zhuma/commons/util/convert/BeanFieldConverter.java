package com.zm.zhuma.commons.util.convert;

import java.lang.annotation.Annotation;

/**
 * @desc 对象属性转换上层接口
 *
 * @author zhumaer
 * @since 2018/9/16
 */
public interface BeanFieldConverter<A extends Annotation, T> {

    void initialize(A ann);

    boolean isNeedConvert(T field);

    T convert(T field);
}
