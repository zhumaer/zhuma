package com.zm.zhuma.commons.attributes.service;

import com.zm.zhuma.commons.attributes.model.AttributesChangement;

import java.util.Map;


/**
 * 属性Service
 *
 * @param <OID> 对象key类型
 */
public interface AttributeService<OID> {

	/**
	 * 获取对象属性
	 * @param objectId 对象id
	 * @param key 属性key
	 * @return 属性值
	 */
	public Object getAttribute(OID objectId, String key);

	/**
	 * 获取对象属性
	 * @param objectId 对象id
	 * @return 属性map，key：属性key，value：属性值
	 */
	public Map<String, Object> getAttributes(OID objectId);

	/**
	 * 获取对象属性
	 * @param objectId 对象id
	 * @param keys 属性keys
	 * @return 属性map，key：属性key，value：属性值
	 */
	public Map<String, Object> getAttributes(OID objectId, Iterable<String> keys);

	/**
	 * 批量获取多个对象的属性
	 * @param objectIds 对象ids
	 * @param keys 属性keys
	 * @return map，key：对象id，value：对象属性map（key：属性key，value：属性值）
	 */
	public Map<OID, Map<String, Object>> getAttributes(Iterable<OID> objectIds, Iterable<String> keys);

	/**
	 * 批量获取多个对象的属性
	 * @param objectIds 对象ids
	 * @return map，key：对象id，value：对象属性map（key：属性key，value：属性值）
	 */
	public Map<OID, Map<String, Object>> getAttributes(Iterable<OID> objectIds);

	/**
	 * 批量获取多个对象的属性
	 * @param objectIds 对象ids
	 * @param key 属性key
	 * @return
	 */
	public Map<OID, Object> getAttributes(Iterable<OID> objectIds, String key);
	
	public Map<OID, Object> getAttributes(Iterable<OID> objectIds, String key, Object value);

	/**
	 * 设置对象属性
	 * @param objectId 对象id
	 * @param key 属性key
	 * @param value 属性值
	 */
	public AttributesChangement<OID> setAttribute(OID objectId, String key, Object value);

	/**
	 * 设置对象属性
	 * 该操作将保存attributes中的属性，不存在于attributes中的属性将删除
	 * @param objectId 对象id
	 * @param attributes 属性map，key：属性key，value：属性值
	 */
	public AttributesChangement<OID> setAttributes(OID objectId, Map<String, Object> attributes);

	/**
	 * 添加对象属性
	 * 该操作将保存attributes中的属性，不存在于attributes中的属性不做任何操作
	 * @param objectId
	 * @param attributes
	 */
	public AttributesChangement<OID> addAttributes(OID objectId, Map<String, Object> attributes);

	/**
	 * 删除单个属性
	 * @param objectId 对象id
	 * @param key 属性key
	 */
	public AttributesChangement<OID> removeAttribute(OID objectId, String key);

	/**
	 * 删除对象属性
	 * @param objectId 对象id
	 */
	public AttributesChangement<OID> removeAttributes(OID objectId);

	/**
	 * 删除对象属性
	 * @param objectId 对象id
	 * @param keys 属性keys
	 */
	public AttributesChangement<OID> removeAttributes(OID objectId, Iterable<String> keys);

}
