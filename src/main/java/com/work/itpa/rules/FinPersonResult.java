package com.work.itpa.rules;

import java.util.ArrayList;
import java.util.List;

public class FinPersonResult {

	boolean status;

	List<String> messages = new ArrayList<String>();;

	public void addMessage(String message) {
		messages.add(message);
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FinPersonResult [status=" + status + ", messages=" + messages + "]";
	}

}
