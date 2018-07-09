package com.zm.zhuma.commons.attributes.service;

import com.zm.zhuma.commons.attributes.model.AttributesChange;

import java.util.Map;

/**
 * @desc 更新属性服务
 *
 * @author zhuamer
 * @since 7/9/2018 11:13
 */
public interface UpdateAttributeService<OID> {

	/**
	 * 设置对象属性
	 * @param objectId 对象id
	 * @param key 属性key
	 * @param value 属性值
	 */
	AttributesChange<OID> setAttribute(OID objectId, String key, Object value);

	/**
	 * 设置对象属性
	 * 该操作将保存attributes中的属性，不存在于attributes中的属性将删除
	 * @param objectId 对象id
	 * @param attributes 属性map，key：属性key，value：属性值
	 */
	AttributesChange<OID> setAttributes(OID objectId, Map<String, Object> attributes);

}
