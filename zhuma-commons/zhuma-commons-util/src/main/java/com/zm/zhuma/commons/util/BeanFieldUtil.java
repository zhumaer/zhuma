package com.zm.zhuma.commons.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zm.zhuma.commons.util.annotations.Convert;
import com.zm.zhuma.commons.util.annotations.FullToHalf;
import com.zm.zhuma.commons.util.annotations.Trim;
import com.zm.zhuma.commons.util.test.BaseSourceBean;
import com.zm.zhuma.commons.util.test.SourceBean;
import com.zm.zhuma.commons.util.test.SourceBean2;
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
 * @desc Bean 字段操作工具类（支持添加{@link FullToHalf} 注解）
 *
 * @author zhuxiaoma
 * @since 7/6/2017 3:13 PM
 */
@Slf4j
public class BeanFieldUtil {

	private static Map<Class<? extends Annotation>, BeanFieldConverter> supportAnnMap = Maps.newHashMap();

	static {
		supportAnnMap.put(FullToHalf.class, new FullToHalf.Converter());
		supportAnnMap.put(Trim.class, new Trim.Converter());
	}

	public static void autoConvert(Object target) {
		autoConvert(target, true,null);
	}

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

            PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(targetClazz, targetPd.getName());
            if (sourcePd == null) {
                continue;
            }

            Method readMethod = sourcePd.getReadMethod();
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
                            } catch (ClassCastException e) {//TODO 此种方式应该想办法替换下
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

	public static void main(String[] args) {
		SourceBean sourceBean = SourceBean.builder()
				.name("１ａｄｓｆａｄｓ阿萨德刚1   ")
				.build();

		sourceBean.setBaseName("  1ｄｓｆａ  asdf啊啊发的");

//        List<SourceBean> list = Lists.newArrayList();
//        list.add(sourceBean);

        Set<BaseSourceBean> baseSourceBeanList = Sets.newHashSet();
        SourceBean2 sourceBean21 = new SourceBean2();
        sourceBean21.setName("     Ａ    ");
        sourceBean21.setTrimName("  111  ");
        baseSourceBeanList.add(sourceBean21);

        sourceBean.setBaseSourceBeanList(baseSourceBeanList);

        SourceBean[] sourceBeanArr = new SourceBean[]{sourceBean};
		BeanFieldUtil.autoConvert(sourceBeanArr);
		System.out.println(sourceBean.toString());
	}
}
