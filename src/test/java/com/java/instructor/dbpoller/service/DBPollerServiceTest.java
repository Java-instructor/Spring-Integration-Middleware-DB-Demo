package com.java.instructor.dbpoller.service;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.instructor.dbpoller.channels.DBPollerChannel;
import com.java.instructor.dbpoller.main.DBPollerMain;
import com.java.instructor.dbpoller.model.MessageHolder;
import com.java.instructor.dbpoller.service.DBPollerService;
import com.java.instructor.dbpoller.utils.MessageUtil;




@RunWith(SpringRunner.class)
@SpringBootTest (classes= { DBPollerMain.class,DBPollerChannel.class, DBPollerService.class})
@DirtiesContext
public class DBPollerServiceTest {

	private final Logger log = LoggerFactory.getLogger(DBPollerServiceTest.class);

	private final String LOG_CHANNEL_SERVICE = "logChannelDBPoller";
	private final String ERROR_CHANNEL = "errorChannel";
	
	String testCaseResult  = null;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	ConfigurableApplicationContext ctx;
	MessageHolder msgHolder;

	@Before
	public void setupMessage() throws IOException {			
		
		Map<String, Object> additionalHeaders = new HashMap<>();		
		additionalHeaders.put("_currentTimestamp", "<root>1664191220379</root>");
		additionalHeaders.put("Timestamp", "<root><StartTime>"+new Date().getTime()+"</StartTime></root>");    
        additionalHeaders.put("_globalVariables", "<GlobalVariables><SharedResources><Connections><ADBTimeout>100.0</ADBTimeout><AIMDBSlowResponseTime>10</AIMDBSlowResponseTime></Connections></SharedResources></GlobalVariables>");
        final Message message = MessageBuilder.withPayload("<root>Hello CreateAIMQuery Message</root>").build();
		msgHolder = MessageUtil.prepare(message, "<root>Hello CreateAIMQuery Message</root>", null, null, null);
		msgHolder.setReturnChannel("end_channel_CreateAIMQuery");
		msgHolder.setAdditionalHeaders(additionalHeaders);
		msgHolder.getAdditionalHeaders().putAll(additionalHeaders);
	}

	@Test
	public void dbPollerServiceTestCase() throws InterruptedException {
		jdbcTemplate.update("delete from xmlFileStore;");
		SubscribableChannel  log_channel = 	(SubscribableChannel) context.getBean(LOG_CHANNEL_SERVICE);	
		SubscribableChannel  errorChannel = 	(SubscribableChannel) context.getBean(ERROR_CHANNEL);	
		
		log_channel.subscribe(new MessageHandler() {
			@SuppressWarnings("deprecation")
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				log.info("handleMessage started ");
				try {
				//String sql = "SELECT status FROM xmlFileStore WHERE fileName=?";
				 String sql ="Select * from HELLO_VIEW ";
				String fileName  = "1.xml";
			    //String result = (String) jdbcTemplate.queryForObject(sql, String.class, fileName);
				List resulta = (List) jdbcTemplate.queryForList(sql);
			    //testCaseResult = result;
			    System.out.println("____________________________________________________________________________________");
			    System.out.println(" result:"+resulta);
			    System.out.println("____________________________________________________________________________________");
			    
				}
				catch (Exception e) {
					System.err.print(e);
					Assert.fail("DB Query not execute !");
				}
				
				
			}
		});
		
		errorChannel.subscribe(new MessageHandler() {
			@SuppressWarnings("deprecation")
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				MessageHolder holder = (MessageHolder) message.getPayload();
				fail("Error ocurred in running ErrorHandlerTestCase: " + holder.getCurrentMessage());
			}
		});
		
		jdbcTemplate.update(
				"INSERT INTO xmlFileStore (fileName, content, description) VALUES ('1.xml', '<test>', 'test')");
		log.info("INSERTED record ");
		
	    Thread.sleep(15000);
	    String expectedResult ="1";
		System.out.println(" result ================================" +testCaseResult );
		Assert.assertEquals(testCaseResult, expectedResult);

	}

}
