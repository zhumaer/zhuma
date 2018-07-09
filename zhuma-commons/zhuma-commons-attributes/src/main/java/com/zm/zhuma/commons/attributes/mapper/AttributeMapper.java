package com.zm.zhuma.commons.attributes.mapper;

import java.util.List;

import com.zm.zhuma.commons.attributes.model.Attribute;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeMapper<OID> {

	void addAttributes(@Param(value = "tableName") String tableName, @Param(value = "attributes") List<Attribute<OID>> attributes);

	void deleteAttributes(@Param(value = "tableName") String tableName, @Param(value = "objectId") OID objectId, @Param(value = "keys") List<String> keys);

	void updateAttributes(@Param(value = "tableName") String tableName, @Param("attr") Attribute<OID> attribute);

	List<Attribute<OID>> getAttributeMapByKeys(@Param(value = "tableName") String tableName, @Param(value = "objectIds") List<OID> objectIds, @Param(value = "keys") List<String> keys);

	List<Attribute<OID>> getAttributeMapByKeyAndValue(@Param(value = "tableName") String tableName, @Param(value = "objectIds") List<OID> objectIds, @Param(value = "key") String key, @Param(value = "value") Object value);

	List<Attribute<OID>> getAttributeMapByKeyAndValues(@Param(value = "tableName") String tableName, @Param(value = "key") String key, @Param(value = "values") List<Object> values);

}