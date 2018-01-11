package com.zm.zhuma.commons.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Collection;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * 校验字段在可允许的值内
 * 备注：被标记的字段可以是任意类型，但最终都会将其转化为string做比较
 *
 * Created by zhumaer on 17/6/5.
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowableValue.Validator.class)
public @interface AllowableValue {

	String message() default "值是无效的，必须是{values}其中一个值";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] values() default {};

	class Validator implements ConstraintValidator<AllowableValue, Object> {

		private Collection<String> allowableValues;

		@Override
		public void initialize(AllowableValue allowable) {
			allowableValues = Arrays.asList(allowable.values());
		}

		@Override
		public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
			if (value == null || allowableValues.size() == 0) {
				return Boolean.TRUE;
			}

			String defaultMessage = constraintValidatorContext.getDefaultConstraintMessageTemplate();
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(defaultMessage.replace("{values}", allowableValues.toString())).addConstraintViolation();

			return allowableValues.contains(value.toString());
		}

	}

}
