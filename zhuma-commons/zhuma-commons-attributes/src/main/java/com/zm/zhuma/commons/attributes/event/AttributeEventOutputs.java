package com.zm.zhuma.commons.attributes.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AttributeEventOutputs {

	@Output(AttributeEventChannels.ATTRIBUTES)
	MessageChannel posted();

}