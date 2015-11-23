package kr.or.javacafe.core.manager.queue.sender;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import kr.or.javacafe.core.manager.queue.env.MessageType;


public class ConfigMessageSender extends MessageSender {

	
	public ConfigMessageSender() throws IOException, TimeoutException {
		super();
	}


	@Override
	protected MessageType getMessageType() {
		return MessageType.SERVER_CONFIG;
	}

	

    
}
