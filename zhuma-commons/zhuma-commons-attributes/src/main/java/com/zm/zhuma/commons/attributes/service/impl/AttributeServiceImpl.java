package com.zm.zhuma.commons.attributes.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.zm.zhuma.commons.attributes.dao.AttributeDao;
import com.zm.zhuma.commons.attributes.event.publisher.AttributeEventPublisher;
import com.zm.zhuma.commons.attributes.model.Attribute;
import com.zm.zhuma.commons.attributes.model.AttributeChangement;
import com.zm.zhuma.commons.attributes.model.AttributesChangedEvent;
import com.zm.zhuma.commons.attributes.model.AttributesChangement;
import com.zm.zhuma.commons.attributes.service.AttributeService;

import com.google.common.collect.Lists;

import com.zm.zhuma.commons.utils.CollectionUtil;
import com.zm.zhuma.commons.utils.StringUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class AttributeServiceImpl<OID> implements AttributeService<OID> {

	private String table = null;

	private AttributeDao<OID> attributeDao;

	private AttributeEventPublisher<OID> eventPublisher;

	@Override
	public Object getAttribute(OID objectId, String key) {
		List<Attribute<OID>> list = attributeDao.getAttrMapByKeys(table, Lists.newArrayList(objectId),
				Lists.newArrayList(key));
		if (!CollectionUtil.isEmpty(list)) {
			return convertType(list.get(0));
		}
		return null;
	}

	@Override
	public Map<String, Object> getAttributes(OID objectId) {
		List<Attribute<OID>> list = attributeDao.getAttrMapByKeys(table, Lists.newArrayList(objectId), null);

		Map<String, Object> map = new HashMap<>(16);
		if (!CollectionUtil.isEmpty(list)) {
			for (Attribute<OID> attribute : list) {
				map.put(attribute.getKey(), convertType(attribute));
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> getAttributes(OID objectId, Iterable<String> keys) {
		List<Attribute<OID>> list = attributeDao.getAttrMapByKeys(table, Lists.newArrayList(objectId),
				Lists.newArrayList(keys));

		Map<String, Object> map = new HashMap<>(16);
		if (!CollectionUtil.isEmpty(list)) {
			for (Attribute<OID> attribute : list) {
				map.put(attribute.getKey(), convertType(attribute));
			}
		}
		return map;
	}

	@Override
	public Map<OID, Map<String, Object>> getAttributes(Iterable<OID> objectIds, Iterable<String> keys) {
		Map<OID, Map<String, Object>> map = new HashMap<>(16);

		// final Iterable<String> attrKeys = keys;

		objectIds.forEach(objectId -> {
			Map<String, Object> partMap = getAttributes(objectId, keys);
			map.put(objectId, partMap);
		});

		return map;
	}

	@Override
	public Map<OID, Map<String, Object>> getAttributes(Iterable<OID> objectIds) {
		Map<OID, Map<String, Object>> map = new HashMap<>(16);

		objectIds.forEach(objectId -> {
			Map<String, Object> partMap = getAttributes(objectId);
			map.put(objectId, partMap);
		});

		return map;
	}

	@Override
	public Map<OID, Object> getAttributes(Iterable<OID> objectIds, String key) {
		List<OID> objIds = Lists.newArrayList();
		List<String> keys = Lists.newArrayList();
		if (null != objectIds) {
			objIds = Lists.newArrayList(objectIds);
		}
		if (StringUtil.isNotEmpty(key)) {
			keys = Lists.newArrayList(key);
		}
		List<Attribute<OID>> list = attributeDao.getAttrMapByKeys(table, objIds, keys);

		Map<OID, Object> map = new HashMap<>(16);
		if (!CollectionUtil.isEmpty(list)) {
			for (Attribute<OID> attribute : list) {
				map.put(attribute.getObjectId(), convertType(attribute));
			}
		}
		return map;
	}

	@Override
	public AttributesChangement<OID> setAttribute(OID objectId, String key, Object value) {
		Map<String, AttributeChangement> added = new HashMap<>(16);
		Map<String, AttributeChangement> updated = new HashMap<>(16);
		Map<String, AttributeChangement> removed = new HashMap<>(16);

		List<Attribute<OID>> addAttrList = Lists.newArrayList();
		Attribute<OID> updateAttr = new Attribute<>();
		List<String> removeKeyList = Lists.newArrayList();

		Map<String, Object> previousMap = getAttributes(objectId);

		Attribute<OID> attribute = new Attribute<>();
		if (!CollectionUtil.isEmpty(previousMap)) {
			if (previousMap.containsKey(key)) {
				for (Map.Entry<String, Object> entry : previousMap.entrySet()) {
					attribute = new Attribute<>();
					if (entry.getKey().equals(key)) {
						updated.put(entry.getKey(),
								AttributeChangement.builder().previous(entry.getValue()).current(value).build());
						attribute.setKey(key);
						attribute.setObjectId(objectId);
						convertType(value, attribute);
						updateAttr = attribute;
					} else {
						removed.put(entry.getKey(),
								AttributeChangement.builder().previous(entry.getValue()).current(value).build());
						removeKeyList.add(entry.getKey());
					}
				}
			} else {
				added.put(key, AttributeChangement.builder().previous(null).current(value).build());
				attribute.setKey(key);
				attribute.setObjectId(objectId);
				convertType(value, attribute);
				addAttrList.add(attribute);
				for (Map.Entry<String, Object> entry : previousMap.entrySet()) {
					removed.put(entry.getKey(),
							AttributeChangement.builder().previous(entry.getValue()).current(value).build());

					removeKeyList.add(entry.getKey());
				}
			}
		} else {
			added.put(key, AttributeChangement.builder().previous(null).current(value).build());

			attribute.setKey(key);
			attribute.setObjectId(objectId);
			convertType(value, attribute);
			addAttrList.add(attribute);
		}

		if (!CollectionUtil.isEmpty(addAttrList)) {
			attributeDao.addAttrs(table, addAttrList);
		}
		if (null != updateAttr && StringUtil.isNotEmpty(updateAttr.getKey())) {
			attributeDao.updateAttrs(table, updateAttr);
		}
		if (!CollectionUtil.isEmpty(removeKeyList)) {
			attributeDao.deleteAttrs(table, objectId, removeKeyList);
		}

		return backResult(objectId, added, updated, removed);
	}

	@Override
	public AttributesChangement<OID> setAttributes(OID objectId, Map<String, Object> attributes) {
		Map<String, AttributeChangement> added = new HashMap<>(16);
		Map<String, AttributeChangement> updated = new HashMap<>(16);
		Map<String, AttributeChangement> removed = new HashMap<>(16);

		Map<String, Object> previousMap = getAttributes(objectId);

		List<String> previousKeyList = Lists.newArrayList();
		List<String> currentKeyList = Lists.newArrayList();
		if (!CollectionUtil.isEmpty(previousMap)) {
			previousKeyList = previousMap.keySet().stream().map(c -> {
				return c;
			}).collect(Collectors.toList());
		}
		if (!CollectionUtil.isEmpty(attributes)) {
			currentKeyList = attributes.keySet().stream().map(c -> {
				return c;
			}).collect(Collectors.toList());
		}

		List<String> addKeyList = CollectionUtil.subtract(currentKeyList,
				previousKeyList);
		List<String> updateKeyList = CollectionUtil.intersection(currentKeyList,
				previousKeyList);
		List<String> removeKeyList = CollectionUtil.subtract(previousKeyList,
				currentKeyList);

		List<Attribute<OID>> addAttrList = addKeyList.stream().map(c -> {
			Attribute<OID> attribute = new Attribute<>();
			attribute.setKey(c);
			attribute.setObjectId(objectId);
			convertType(attributes.get(c), attribute);

			added.put(c, AttributeChangement.builder().previous(null).current(attributes.get(c)).build());

			return attribute;
		}).collect(Collectors.toList());

		updateKeyList.forEach(c -> {
			Attribute<OID> attribute = new Attribute<>();
			attribute.setKey(c);
			attribute.setObjectId(objectId);
			convertType(attributes.get(c), attribute);

			attributeDao.updateAttrs(table, attribute);

			updated.put(c,
					AttributeChangement.builder().previous(previousMap.get(c)).current(attributes.get(c)).build());
		});

		if (!CollectionUtil.isEmpty(addAttrList)) {
			attributeDao.addAttrs(table, addAttrList);
		}
		if (!CollectionUtil.isEmpty(removeKeyList)) {
			removeKeyList.forEach(c -> {
				removed.put(c, AttributeChangement.builder().previous(previousMap.get(c)).current(null).build());
			});
			attributeDao.deleteAttrs(table, objectId, removeKeyList);
		}

		return backResult(objectId, added, updated, removed);
	}

	@Override
	public AttributesChangement<OID> addAttributes(OID objectId, Map<String, Object> attributes) {
		Map<String, AttributeChangement> added = new HashMap<>(16);
		Map<String, AttributeChangement> updated = new HashMap<>(16);
		Map<String, AttributeChangement> removed = new HashMap<>(16);

		if (!CollectionUtil.isEmpty(attributes)) {
			Map<String, Object> previousMap = getAttributes(objectId);

			List<String> previousKeyList = Lists.newArrayList();

			if (!CollectionUtil.isEmpty(previousMap)) {
				previousKeyList = previousMap.keySet().stream().map(c -> {
					return c;
				}).collect(Collectors.toList());
			}

			List<String> currentKeyList = attributes.keySet().stream().map(c -> {
				return c;
			}).collect(Collectors.toList());

			List<String> addKeyList = CollectionUtil.subtract(currentKeyList,
					previousKeyList);
			List<String> updateKeyList = CollectionUtil.intersection(currentKeyList,
					previousKeyList);
			List<Attribute<OID>> addAttrList = addKeyList.stream().map(c -> {
				Attribute<OID> attribute = new Attribute<>();
				attribute.setKey(c);
				attribute.setObjectId(objectId);
				convertType(attributes.get(c), attribute);

				added.put(c, AttributeChangement.builder().previous(null).current(attributes.get(c)).build());

				return attribute;
			}).collect(Collectors.toList());
			if (!CollectionUtil.isEmpty(addAttrList)) {
				attributeDao.addAttrs(table, addAttrList);
			}

			updateKeyList.forEach(c -> {
				Attribute<OID> attribute = new Attribute<>();
				attribute.setKey(c);
				attribute.setObjectId(objectId);
				convertType(attributes.get(c), attribute);

				attributeDao.updateAttrs(table, attribute);

				updated.put(c,
						AttributeChangement.builder().previous(previousMap.get(c)).current(attributes.get(c)).build());
			});
		}

		return backResult(objectId, added, updated, removed);
	}

	@Override
	public AttributesChangement<OID> removeAttribute(OID objectId, String key) {
		Map<String, AttributeChangement> added = new HashMap<>(16);
		Map<String, AttributeChangement> updated = new HashMap<>(16);
		Map<String, AttributeChangement> removed = new HashMap<>(16);

		List<String> removeKeyList = Lists.newArrayList();

		Map<String, Object> previousMap = getAttributes(objectId);

		if (!CollectionUtil.isEmpty(previousMap) && previousMap.containsKey(key)) {
			removed.put(key, AttributeChangement.builder().previous(previousMap.get(key)).current(null).build());
			removeKeyList.add(key);
			attributeDao.deleteAttrs(table, objectId, removeKeyList);
		}

		attributeDao.deleteAttrs(table, objectId, Lists.newArrayList(key));

		return backResult(objectId, added, updated, removed);
	}

	@Override
	public AttributesChangement<OID> removeAttributes(OID objectId) {
		Map<String, AttributeChangement> added = new HashMap<>(16);
		Map<String, AttributeChangement> updated = new HashMap<>(16);
		Map<String, AttributeChangement> removed = new HashMap<>(16);

		List<String> removeKeyList = Lists.newArrayList();

		Map<String, Object> previousMap = getAttributes(objectId);

		if (!CollectionUtil.isEmpty(previousMap)) {
			for (Map.Entry<String, Object> entry : previousMap.entrySet()) {
				removed.put(entry.getKey(),
						AttributeChangement.builder().previous(entry.getValue()).current(null).build());
				removeKeyList.add(entry.getKey());
			}
			if (!CollectionUtil.isEmpty(removeKeyList)) {
				attributeDao.deleteAttrs(table, objectId, removeKeyList);
			}
		}

		return backResult(objectId, added, updated, removed);
	}

	@Override
	public AttributesChangement<OID> removeAttributes(OID objectId, Iterable<String> keys) {
		Map<String, AttributeChangement> added = new HashMap<>(16);
		Map<String, AttributeChangement> updated = new HashMap<>(16);
		Map<String, AttributeChangement> removed = new HashMap<>(16);

		Map<String, Object> previousMap = getAttributes(objectId);

		List<String> previousKeyList = previousMap.keySet().stream().map(c -> {
			return c;
		}).collect(Collectors.toList());
		List<String> currentKeyList = (List<String>) keys;

		List<String> removeKeyList = CollectionUtil.intersection(previousKeyList,
				currentKeyList);

		if (!CollectionUtil.isEmpty(previousMap) && !CollectionUtil.isEmpty(removeKeyList)) {
			for (String key : removeKeyList) {
				removed.put(key, AttributeChangement.builder().previous(previousMap.get(key)).current(null).build());
			}
			attributeDao.deleteAttrs(table, objectId, removeKeyList);
		}

		return backResult(objectId, added, updated, removed);
	}

	/** 保存扩展属性时对象类型转换 */
	public void convertType(Object obj, Attribute<OID> attribute) {
		String type = null;
		String value = null;

		if (obj instanceof Integer) {
			type = Integer.class.getSimpleName();
			value = obj.toString();
		} else if (obj instanceof Long) {
			type = Long.class.getSimpleName();
			value = obj.toString();
		} else if (obj instanceof Date) {
			type = Date.class.getSimpleName();
			Date date = (Date) obj;
			value = date.getTime() + "";
		} else if (obj instanceof Double) {
			type = Double.class.getSimpleName();
			value = obj.toString();
		} else if (obj instanceof Boolean) {
			type = Boolean.class.getSimpleName();
			value = obj.toString();
		} else if (obj instanceof String) {
			type = String.class.getSimpleName();
			value = obj.toString();
		} else {
			type = String.class.getSimpleName();
			if (obj == null) {
				value = "";
			} else {
				value = obj.toString();
			}
		}

		attribute.setType(type);
		attribute.setValue(value);
	}

	/**
	 * 返回值类型转换
	 * 
	 * @param attribute
	 * @return
	 */
	public Object convertType(Attribute<OID> attribute) {
		String type = attribute.getType();
		String value = attribute.getValue();
		Object result = null;

		if (Integer.class.getSimpleName().equals(type)) {
			result = Integer.valueOf(value);
		} else if (Long.class.getSimpleName().equals(type)) {
			result = Long.valueOf(value);
		} else if (Date.class.getSimpleName().equals(type)) {
			Date date = new Date(Long.valueOf(value));
			result = date;
		} else if (Double.class.getSimpleName().equals(type)) {
			result = Double.valueOf(value);
		} else if (Boolean.class.getSimpleName().equals(type)) {
			result = Boolean.valueOf(value);
		} else if (String.class.getSimpleName().equals(type)) {
			result = String.valueOf(value);
		} else {
			result = value;
		}

		return result;
	}

	private AttributesChangement<OID> backResult(OID objectId, Map<String, AttributeChangement> added,
			Map<String, AttributeChangement> updated, Map<String, AttributeChangement> removed) {
		AttributesChangement<OID> result = new AttributesChangement<>();
		result.setObjectId(objectId);
		result.setAdded(added);
		result.setUpdated(updated);
		result.setRemoved(removed);

		if (!CollectionUtil.isEmpty(added) || !CollectionUtil.isEmpty(updated) || !CollectionUtil.isEmpty(removed)) {
			sendAttributesChangeEvent(result);
		}

		return result;
	}

	/**
	 * 发送属性变更消息
	 * @param attributesChangement
	 */
	private void sendAttributesChangeEvent(AttributesChangement<OID> attributesChangement) {
		AttributesChangedEvent<OID> event = AttributesChangedEvent.<OID> builder().data(attributesChangement)
				.occurredTime(new Date()).build();
		eventPublisher.publishAttributesChangedEvent(event, table);
		log.debug("{} is published.", event);
	}

	@Override
	public Map<OID, Object> getAttributes(Iterable<OID> objectIds, String key, Object value) {
		List<OID> objIds = Lists.newArrayList();
		if (null != objectIds) {
			objIds = Lists.newArrayList(objectIds);
		}
		List<Attribute<OID>> list = attributeDao.getAttrMapByKeyAndValue(table, objIds, key, value);;

		Map<OID, Object> map = new HashMap<>(16);
		if (!CollectionUtil.isEmpty(list)) {
			for (Attribute<OID> attribute : list) {
				map.put(attribute.getObjectId(), convertType(attribute));
			}
		}
		return map;
	}
}