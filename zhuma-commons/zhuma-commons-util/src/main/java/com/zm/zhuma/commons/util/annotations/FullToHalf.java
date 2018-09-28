package com.zm.zhuma.commons.util.annotations;

import com.zm.zhuma.commons.util.AsciiUtils;
import com.zm.zhuma.commons.util.convert.BeanFieldConverter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @desc 半角替全角
 *
 * @author zhumaer
 * @since 2018/9/16
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FullToHalf {

    /**
     * 转换器
     */
    class Converter implements BeanFieldConverter<FullToHalf, String> {

        @Override
        public void initialize(FullToHalf ann) {

        }

        @Override
        public boolean isNeedConvert(String field) {
            return AsciiUtils.existFullChar(field);
        }

        @Override
        public String convert(String field) {
            return AsciiUtils.full2Half(field);
        }
    }
}
