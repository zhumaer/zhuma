package com.zm.zhuma.commons.attributes.event.publisher;


import com.zm.zhuma.commons.attributes.model.AttributesChangedEvent;

public interface AttributeEventPublisher<OID> {

	void publishAttributesChangedEvent(AttributesChangedEvent<OID> event, String tableName);

}
