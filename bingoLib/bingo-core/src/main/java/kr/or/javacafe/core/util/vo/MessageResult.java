package kr.or.javacafe.core.util.vo;

import java.util.Date;

import kr.or.javacafe.core.manager.queue.env.MessageType;

public class MessageResult {

	private String result;
	private MessageType messageType;
	private Date messageCreateTime; 
	private Object data;
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public Date getMessageCreateTime() {
		return messageCreateTime;
	}
	public void setMessageCreateTime(Date messageCreateTime) {
		this.messageCreateTime = messageCreateTime;
	}
	
	


	
	
}
