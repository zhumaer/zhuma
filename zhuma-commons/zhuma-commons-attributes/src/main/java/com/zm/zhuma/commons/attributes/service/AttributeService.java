package com.zm.zhuma.commons.attributes.service;

/**
 * @desc 通用属性服务
 *
 * @author zhuamer
 * @since 7/9/2018 11:13
 */
public interface AttributeService<OID> extends
		InsertAttributeService<OID>,
		DeleteAttributeService<OID>,
		UpdateAttributeService<OID>,
		SelectAttributeService<OID> {
}
