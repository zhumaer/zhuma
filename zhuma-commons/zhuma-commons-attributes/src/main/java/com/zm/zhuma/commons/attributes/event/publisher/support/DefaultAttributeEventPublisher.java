package com.zm.zhuma.commons.attributes.event.publisher.support;

import java.util.Map;

import com.zm.zhuma.commons.attributes.event.AttributeEventOutputs;
import com.zm.zhuma.commons.attributes.event.publisher.AttributeEventPublisher;
import com.zm.zhuma.commons.attributes.model.AttributesChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DefaultAttributeEventPublisher<OID> implements AttributeEventPublisher<OID> {

	@Autowired
	private AttributeEventOutputs outputs;

	@Override
	public void publishAttributesChangedEvent(AttributesChangedEvent<OID> event, String tableName) {
		String routingKey = "attributes." + tableName;
		outputs.posted().send(toMessage(routingKey, event));
		log.info("attributes_changement_event,routingKey={},event={}",routingKey,event);
	}

	private Message<?> toMessage(String routingKey, Object obj) {
		Map<String, Object> headers = Maps.newHashMap();
		headers.put("routingKey", routingKey);
		return MessageBuilder.withPayload(obj).copyHeaders(headers).build();
	}

}
