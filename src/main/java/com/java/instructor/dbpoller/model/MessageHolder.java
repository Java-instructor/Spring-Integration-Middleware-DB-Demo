package com.java.instructor.dbpoller.model;

import java.util.Map;

public class MessageHolder {

	Object orginalMessage;
	Object previousMessage;
	Object currentMessage;
	String currentInputChannelName;
	String currentOutputChannelName;
	String returnChannel;
	String errorChannel;

	Map<String, Object> additionalHeaders;

	public String getErrorChannel() {
		return errorChannel;
	}

	public void setErrorChannel(String errorChannel) {
		this.errorChannel = errorChannel;
	}

	public Object getOrginalMessage() {
		return orginalMessage;
	}

	public void setOrginalMessage(Object orginalMessage) {
		this.orginalMessage = orginalMessage;
	}

	public Object getPreviousMessage() {
		return previousMessage;
	}

	public void setPreviousMessage(Object previousMessage) {
		this.previousMessage = previousMessage;
	}

	public Object getCurrentMessage() {
		return currentMessage;
	}

	public void setCurrentMessage(Object currentMessage) {
		this.currentMessage = currentMessage;
	}

	public String getCurrentInputChannelName() {
		return currentInputChannelName;
	}

	public void setCurrentInputChannelName(String currentInputChannelName) {
		this.currentInputChannelName = currentInputChannelName;
	}

	public String getCurrentOutputChannelName() {
		return currentOutputChannelName;
	}

	public void setCurrentOutputChannelName(String currentOutputChannelName) {
		this.currentOutputChannelName = currentOutputChannelName;
	}

	public String getReturnChannel() {
		return returnChannel;
	}

	public void setReturnChannel(String returnChannel) {
		this.returnChannel = returnChannel;
	}

	public Map<String, Object> getAdditionalHeaders() {
		return additionalHeaders;
	}

	public void setAdditionalHeaders(Map<String, Object> additionalHeaders) {
		this.additionalHeaders = additionalHeaders;
	}

	/*
	 * @Override public String toString() { return "MessageHolder [orginalMessage="
	 * + orginalMessage + ", previousMessage=" + previousMessage +
	 * ", currentMessage=" + currentMessage + ", currentInputChannelName=" +
	 * currentInputChannelName + ", currentOutputChannelName=" +
	 * currentOutputChannelName + ", returnChannel=" + returnChannel +
	 * ", additionalHeaders=" + additionalHeaders + "]"; }
	 */
	@Override
	public String toString() {
		return "MessageHolder [currentMessage=" + currentMessage + "]";
	}

}
