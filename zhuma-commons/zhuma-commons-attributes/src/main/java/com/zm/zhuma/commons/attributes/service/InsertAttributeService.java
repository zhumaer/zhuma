package com.zm.zhuma.commons.attributes.service;

import com.zm.zhuma.commons.attributes.model.AttributesChange;

import java.util.Map;

/**
 * @desc 插入属性服务
 *
 * @author zhuamer
 * @since 7/9/2018 11:13
 */
public interface InsertAttributeService<OID> {

	/**
	 * 添加对象属性
	 * 该操作将保存attributes中的属性，不存在于attributes中的属性不做任何操作
	 * @param objectId 对象id
	 * @param attributes 属性集合
	 */
	AttributesChange<OID> addAttributes(OID objectId, Map<String, Object> attributes);

}
