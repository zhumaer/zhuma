package com.zm.zhuma.commons.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import com.zm.zhuma.commons.annotations.FieldAlias;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * @desc Bean操作工具类（拷贝方法时支持添加{@link FieldAlias} 注解）
 *
 * @author zhumaer
 * @since 7/6/2017 3:13 PM
 */
public class BeanUtil {

	public static void copyProperties(Object source, Object target, String... ignoreProperties) throws BeansException {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), target.getClass(), targetPd);
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						} catch (Throwable ex) {
							throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}

	}

	private static PropertyDescriptor getPropertyDescriptor(Class<?> sourceClazz, Class<?> targetClazz, PropertyDescriptor targetPd) {
		PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(sourceClazz, targetPd.getName());
		if (sourcePd == null) {
			try {
				Field field = getDeclaredField(targetClazz, targetPd.getName());
				FieldAlias[] fieldAliases = field.getAnnotationsByType(FieldAlias.class);
				if (fieldAliases == null) {
					return null;
				}

				for (FieldAlias fieldAlias : fieldAliases) {
					if (ArrayUtils.isEmpty(fieldAlias.sourceClass()) || ArrayUtils.contains(fieldAlias.sourceClass(), sourceClazz)) {
						sourcePd = BeanUtils.getPropertyDescriptor(sourceClazz, fieldAlias.value());
						if (sourcePd != null) {
							return sourcePd;
						}
					}
				}
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return sourcePd;
	}

	/**
	 * 获取某个类下的属性（包含父类）
	 * */
	public static Field getDeclaredField(Class<?> targetClazz, String fieldName) throws NoSuchFieldException {
		Class<?> clazz = targetClazz;
		while (clazz != Object.class) {
			try {
				return clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			}
		}

		throw new NoSuchFieldException();
	}

}
