package com.zm.zhuma.commons.util.annotations;


import com.zm.zhuma.commons.util.BeanFieldConverter;
import com.zm.zhuma.commons.util.StringUtil;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @desc 字符串去两边空格
 * 
 * @author zhuxiaoma
 * @since 9/12/2018 3:13 PM
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trim {

    /**
     * 转换器
     */
    class Converter implements BeanFieldConverter<Trim, String> {

        @Override
        public void initialize(Trim ann) {

        }

        @Override
        public boolean isNeedConvert(String field) {
            if (StringUtil.isEmpty(field)) {
                return false;
            }
            return field.startsWith(" ") || field.endsWith(" ");
        }

        @Override
        public String convert(String field) {
            return field.trim();
        }
    }

}
