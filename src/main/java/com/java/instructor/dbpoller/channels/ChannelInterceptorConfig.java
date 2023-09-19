package com.java.instructor.dbpoller.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class ChannelInterceptorConfig implements ChannelInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(ChannelInterceptorConfig.class);

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel messageChannel) {
		LOGGER.info("PRE SEND CALLED::" + messageChannel);
		return message;
	}

	@Override
	public void postSend(Message<?> message, MessageChannel messageChannel, boolean sent) {
		if (!sent) {
			LOGGER.info("POST SEND CALLED::" + messageChannel);
			LOGGER.info("Message (" + message + ") was not sent correctly to message channel " + messageChannel);
		}
	}

}
