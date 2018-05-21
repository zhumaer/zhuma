package com.zm.zhuma.commons.util.annotations;


import java.lang.annotation.*;

/**
 * @desc 别名注解 用来为类的字段添加别名（备注：可重复注解，也可以为一个别名指定多个源类）
 * 
 * @author zhumaer
 * @since 7/6/2017 3:13 PM
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(FieldAlias.FieldAliases.class)
public @interface FieldAlias {

	String value();

	Class<?>[] sourceClass() default { };

	/**
	 * @desc 别名注解复数
	 * 
	 * @author zhuamer
	 * @since 7/6/2017 3:13 PM
	 */
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface FieldAliases {

		FieldAlias[] value();

	}
}
