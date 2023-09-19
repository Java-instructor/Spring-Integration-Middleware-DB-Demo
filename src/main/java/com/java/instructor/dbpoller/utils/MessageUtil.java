package com.java.instructor.dbpoller.utils;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.java.instructor.dbpoller.model.MessageHolder;

@Component
public class MessageUtil {

	@Autowired
	Environment env;

	@Autowired
	private ApplicationContext applicationContext;

	public static MessageHolder prepare(final Message<?> inputMessage, Object currentMessage, String inputChannelName,
			String outputChennalName, String returnChannel) {
		if (inputMessage.getPayload() instanceof MessageHolder) {
			MessageHolder omh = (MessageHolder) inputMessage.getPayload();
			MessageHolder mh = new MessageHolder();
			mh.setAdditionalHeaders(omh.getAdditionalHeaders());
			mh.setPreviousMessage(omh.getCurrentMessage());
			mh.setCurrentMessage(currentMessage);
			mh.setOrginalMessage(omh.getOrginalMessage());
			mh.setReturnChannel(returnChannel);
			return mh;
		} else {
			// First time build this message
			MessageHolder mh = new MessageHolder();
			mh.setAdditionalHeaders(new HashMap<String, Object>());
			mh.setPreviousMessage(inputMessage.getPayload());
			mh.setCurrentMessage(currentMessage);
			mh.setOrginalMessage(inputMessage.getPayload());
			mh.setReturnChannel(returnChannel);
			String processContext = MessageUtil.getInitiateProcessContext();
			// String globalVariable=MessageUtil.getGlobalVariables();
			String error = MessageUtil.getErrorReport();
			mh.getAdditionalHeaders().put("$_processContext", processContext);
			mh.getAdditionalHeaders().put("$JMS-Queue-Receiver", inputMessage.getPayload());
			mh.getAdditionalHeaders().put("$_error", error);
			return mh;
		}
	}

	public static String getInitiateProcessContext() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ProcessContext>");
		sb.append(" <ProcessId>12345</ProcessId>");
		sb.append(" <ProjectName>str1234</ProjectName>");
		sb.append("  <EngineName>str1234</EngineName>");
		sb.append("  <RestartedFromCheckpoint>true</RestartedFromCheckpoint>");
		sb.append(" <TrackingInfo>str1234</TrackingInfo>");
		sb.append("  <CustomId>str1234</CustomId>");
		sb.append("</ProcessContext>");
		return sb.toString();
	}

	public static String getErrorReport() {
		String errorReport = "<ErrorReport>\r\n" + "  <StackTrace>str1234</StackTrace>\r\n" + "  <Msg>str1234</Msg>\r\n"
				+ "  <FullClass>str1234</FullClass>\r\n" + "  <Class>str1234</Class>\r\n"
				+ "  <ProcessStack>str1234</ProcessStack>\r\n" + "  <MsgCode>str1234</MsgCode>\r\n"
				+ "  <Data>str1234</Data>\r\n" + "</ErrorReport>";
		return errorReport;
	}

}
