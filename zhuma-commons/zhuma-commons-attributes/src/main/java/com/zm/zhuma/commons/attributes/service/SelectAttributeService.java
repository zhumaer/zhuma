package com.zm.zhuma.commons.attributes.service;

import java.util.Map;

/**
 * @desc 查询属性服务
 *
 * @author zhuamer
 * @since 7/9/2018 11:13
 */
public interface SelectAttributeService<OID> {

	/**
	 * 获取对象所有属性
	 * @param objectId 对象id
	 * @return 属性map，key：属性key，value：属性值
	 */
	Map<String, Object> getAttributes(OID objectId);

	/**
	 * 获取对象所有属性
	 * @param objectId 对象id
	 * @param objectClass 属性对应的类
	 * @return 所有属性对应转化的类对象
	 */
	<T> T getAttributes(OID objectId, Class<T> objectClass);

	/**
	 * 获取对象某一个属性
	 * @param objectId 对象id
	 * @param key 属性key
	 * @return 属性值
	 */
	Object getAttribute(OID objectId, String key);

	/**
	 * 获取对象某一个属性
	 * @param objectId 对象id
	 * @param key 属性key
	 * @param valueClass 属性value类
	 * @return 属性值
	 */
	<V> V getAttribute(OID objectId, String key, Class<V> valueClass);

	/**
	 * 获取对象某一批属性
	 * @param objectId 对象id
	 * @param keys 属性keys
	 * @return 属性map，key：属性key，value：属性值
	 */
	Map<String, Object> getAttributes(OID objectId, Iterable<String> keys);

	/**
	 * 批量获取多个对象的属性
	 * @param objectIds 对象ids
	 * @param keys 属性keys
	 * @return map，key：对象id，value：对象属性map（key：属性key，value：属性值）
	 */
	Map<OID, Map<String, Object>> getAttributes(Iterable<OID> objectIds, Iterable<String> keys);

	/**
	 * 批量获取多个对象的属性
	 * @param objectIds 对象ids
	 * @return map，key：对象id，value：对象属性map（key：属性key，value：属性值）
	 */
	Map<OID, Map<String, Object>> getAttributes(Iterable<OID> objectIds);

	/**
	 * 批量获取多个对象的属性
	 * @param objectIds 对象ids
	 * @param key 属性key
	 * @return
	 */
	Map<OID, Object> getAttributes(Iterable<OID> objectIds, String key);

	/**
	 * 批量获取多个对象ID对应属性信息
	 * @param objectIds 对象ids
	 * @param key 属性key
	 * @return
	 */
	Map<OID, Object> getAttributes(Iterable<OID> objectIds, String key, Object value);

	/**
	 * 一个key，不同value值
	 * @param key
	 * @param values
	 * @return
	 */
	Map<OID, Object> getAttributes(String key, Iterable<Object> values);

}
