package com.zhuma.demo.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zhuma.demo.annotations.FieldAlias.FieldAliases;

/**
 * @desc 别名注解 用来为类的字段添加别名（备注：可重复注解，也可以为一个别名指定多个源类）
 * 
 * @author jingkun.wang@baidao.com
 * @since 7/6/2017 3:13 PM
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(FieldAliases.class)
public @interface FieldAlias {

	String value();

	Class<?>[] sourceClass() default { };

	
	/**
	 * @desc 别名注解复数
	 * 
	 * @author jingkun.wang@baidao.com
	 * @since 7/6/2017 3:13 PM
	 */
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface FieldAliases {

		FieldAlias[] value();

	}
}
