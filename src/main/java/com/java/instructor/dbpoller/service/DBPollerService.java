package com.java.instructor.dbpoller.service;

import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.handler.LoggingHandler.Level;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.Message;

@Configuration
@EnableIntegration
public class DBPollerService {

	private final Logger log = LoggerFactory.getLogger(DBPollerService.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Bean
	@InboundChannelAdapter(value = "jdbcUpdateChannelDBPoller", poller = @Poller(fixedDelay = "10000"))
	public org.springframework.integration.core.MessageSource<?> storedProc(DataSource dataSource) {
		return new JdbcPollingChannelAdapter(dataSource, "SELECT * FROM xmlFileStore where status is null");
	}

	@ServiceActivator(inputChannel = "jdbcUpdateChannelDBPoller", outputChannel = "logChannelDBPoller")
	public Message<?> serviceActivatorForDBOperation(String payload) {

		if (payload.toLowerCase().startsWith("fail")) {
			log.error("Service failure. Test result: {} ", payload);
			throw new RuntimeException("Service failure.");
		}

		log.info("Service success. Test result: {}", payload);
		jdbcTemplate.update("update  xmlFileStore set status= 1");

		final Message<String> message = MessageBuilder.withPayload(payload)
				.setHeader("updateVerified", new Date().toString()).build();
		return message;

	}

	@Bean
	@ServiceActivator(inputChannel = "logChannelDBPoller")
	public LoggingHandler logChannelDBPollerHandler() {
		LoggingHandler adapter = new LoggingHandler(Level.DEBUG);
		adapter.setLoggerName("logChannelDBPollerHandler");
		adapter.setLogExpressionString("'log msg: ' + payload");
		return adapter;
	}

}
