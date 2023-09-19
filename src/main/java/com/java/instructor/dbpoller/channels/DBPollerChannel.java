package com.java.instructor.dbpoller.channels;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@Configuration
@EnableIntegration
public class DBPollerChannel {

	@Bean
	public MessageChannel jdbcUpdateChannelDBPoller() {
		return new DirectChannel();
	}

	@Bean
	public SubscribableChannel logChannelDBPoller() {
		return MessageChannels.publishSubscribe().get();
	}

}
