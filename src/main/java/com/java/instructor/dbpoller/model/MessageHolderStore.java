package com.java.instructor.dbpoller.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageHolderStore {
	Map<String, MessageHolder> messageStore = new HashMap();

	Map<String, MessageHolder> mapOfMessageHolder = new HashMap();

	public Map<String, MessageHolder> getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(Map<String, MessageHolder> messageStore) {
		this.messageStore = messageStore;
	}

	public Map<String, MessageHolder> getMapOfMessageHolder() {
		return mapOfMessageHolder;
	}

	public void setMapOfMessageHolder(Map<String, MessageHolder> mapOfMessageHolder) {
		this.mapOfMessageHolder = mapOfMessageHolder;
	}

	public void addMapOfHolder(String id, MessageHolder holder) {
		if (!getMapOfMessageHolder().containsKey(id)) {
			getMapOfMessageHolder().put(id, holder);
		}
	}
}
