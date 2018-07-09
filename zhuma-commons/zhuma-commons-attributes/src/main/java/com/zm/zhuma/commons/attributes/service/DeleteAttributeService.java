package com.zm.zhuma.commons.attributes.service;

import com.zm.zhuma.commons.attributes.model.AttributesChange;

import java.util.Map;

/**
 * @desc 删除属性服务
 *
 * @author zhuamer
 * @since 7/9/2018 11:13
 */
public interface DeleteAttributeService<OID> {

	/**
	 * 删除单个属性
	 * @param objectId 对象id
	 * @param key 属性key
	 */
	AttributesChange<OID> deleteAttribute(OID objectId, String key);

	/**
	 * 删除对象属性
	 * @param objectId 对象id
	 */
	AttributesChange<OID> deleteAttributes(OID objectId);

	/**
	 * 删除对象属性
	 * @param objectId 对象id
	 * @param keys 属性keys
	 */
	AttributesChange<OID> deleteAttributes(OID objectId, Iterable<String> keys);

}
