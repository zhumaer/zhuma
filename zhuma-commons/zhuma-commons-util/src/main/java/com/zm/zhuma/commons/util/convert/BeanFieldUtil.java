package com.zm.zhuma.commons.util.convert;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zm.zhuma.commons.util.annotations.Convert;
import com.zm.zhuma.commons.util.annotations.FullToHalf;
import com.zm.zhuma.commons.util.annotations.Trim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @desc Bean 字段值转化工具（支持添加{@link FullToHalf} {@link Trim}  等注解）
 *
 * @author zhumaer
 * @since 2018/9/16
 */
@Slf4j
public class BeanFieldUtil {

	private static Map<Class<? extends Annotation>, BeanFieldConverter> supportAnnMap = Maps.newHashMap();

	static {
		supportAnnMap.put(FullToHalf.class, new FullToHalf.Converter());
		supportAnnMap.put(Trim.class, new Trim.Converter());
	}

	/**
     * 根据注解标记转化输入对象的字段值
     *
     * 备注：支持数组、集合、对象嵌对象/集合等情况
     */
	public static void autoConvert(Object target) {
		autoConvert(target, true,null);
	}

    /**
     * 根据注解标记转化输入对象的字段值
     *
     * @Param target 转化目标
     * @Param ignoreException 出异常时是否忽略
     * @Param supportAnnotations 所支持的注解（备注：值为null时，代表支持所有配置的注解）
     */
	public static void autoConvert(Object target, boolean ignoreException, Class<? extends Annotation>... supportAnnotations) {
		Assert.notNull(target, "target cannot be null");

		Object targetProxy = target;

		if (target.getClass().isArray()) {
            targetProxy = new ArrayList<>(Arrays.asList((Object[])targetProxy));
        }

        if (targetProxy instanceof Iterable) {
            Iterable sourceIt = (Iterable)targetProxy;
            sourceIt.forEach(item -> convertBean(item, ignoreException, supportAnnotations));
        } else {
            convertBean(targetProxy, ignoreException, supportAnnotations);
        }

	}

	/**
     * 转化对象
     */
    private static void convertBean(Object target, boolean ignoreException, Class<? extends Annotation>... supportAnnotations) {
        Class<?> targetClazz = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(targetClazz);
        Set<Class<? extends Annotation>> supportAnnotationSet = supportAnnotations != null ? Sets.newHashSet(supportAnnotations) : null;

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod == null) {
                continue;
            }

            Field field;
            try {
                field = getDeclaredField(targetClazz, targetPd.getName());
            } catch (NoSuchFieldException e) {
                continue;
            }

            Method readMethod = targetPd.getReadMethod();
            if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                try {
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }

                    if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                        writeMethod.setAccessible(true);
                    }

                    Annotation[] annotations = field.getAnnotations();
                    if (annotations == null || annotations.length == 0) {
                        continue;
                    }

                    Object value = readMethod.invoke(target);

                    if (hasConvertAnn(annotations)) {
                        autoConvert(value, ignoreException, supportAnnotations);
                        continue;
                    }

                    boolean isModified = false;
                    for (Annotation ann : annotations) {
                        if (supportAnnMap.containsKey(ann.annotationType()) && (supportAnnotationSet == null || supportAnnotationSet.contains(ann.annotationType()))) {
                            BeanFieldConverter beanFieldConverter = supportAnnMap.get(ann.annotationType());

                            beanFieldConverter.initialize(ann);

                            try {
                                if (beanFieldConverter.isNeedConvert(value)) {
                                    value = beanFieldConverter.convert(value);
                                    isModified = true;
                                }
                            } catch (ClassCastException e) {
                                String prompt = String.format("%s annotation cannot be mark on %s field", ann.annotationType().getSimpleName(), value.getClass().getTypeName());
                                throw new RuntimeException(prompt);
                            }

                        }
                    }

                    if (isModified) {
                        writeMethod.invoke(target, value);
                    }
                } catch (Throwable ex) {
                    if (ignoreException) {
                        log.warn("Fail to convertBean, caused by:", ex);
                    } else {
                        throw new RuntimeException("Fail to set property '" + targetPd.getName() + "' to target", ex);
                    }
                }
            }

        }

    }

    /**
     * 是否标记{@link Convert}注解
     */
    private static boolean hasConvertAnn(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Convert.class)) {
                return true;
            }
        }
        return false;
    }

    /**
	 * 获取某个类下的属性（包含父类）
	 */
    private static Field getDeclaredField(Class<?> targetClazz, String fieldName) throws NoSuchFieldException {
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
