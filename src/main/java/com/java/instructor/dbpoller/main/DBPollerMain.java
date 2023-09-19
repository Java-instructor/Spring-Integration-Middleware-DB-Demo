
package com.java.instructor.dbpoller.main;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.java.instructor.dbpoller.channels.ChannelInterceptorConfig;

@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)

@Configuration
@ComponentScan({ "com.java.instructor.dbpoller" })
@EnableIntegration
@IntegrationComponentScan({ "com.java.instructor.dbpoller" })

public class DBPollerMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBPollerMain.class);

	public static void main(final String... args) throws JAXBException {

		ConfigurableApplicationContext context = new SpringApplication(DBPollerMain.class).run(args);
		context.registerShutdownHook();
	}

	@Bean
	@GlobalChannelInterceptor(patterns = { "*" })
	public ChannelInterceptor aChannelInterceptor() {
		return new ChannelInterceptorConfig();
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata defaultPoller() {

		PollerMetadata pollerMetadata = new PollerMetadata();
		pollerMetadata.setTrigger(new PeriodicTrigger(10));
		return pollerMetadata;
	}

}
