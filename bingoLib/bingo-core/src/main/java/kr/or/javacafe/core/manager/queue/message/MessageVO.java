package kr.or.javacafe.core.manager.queue.message;

import java.util.Date;

import kr.or.javacafe.core.manager.queue.env.MessageType;

public abstract class MessageVO {

	private MessageType messageType;
	private Date messageCreateTime;
	
	
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
