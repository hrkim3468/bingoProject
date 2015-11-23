package kr.or.javacafe.core.manager.queue.sender;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import kr.or.javacafe.core.manager.queue.env.MessageType;


public class ResetMessageSender extends MessageSender {

	
	public ResetMessageSender() throws IOException, TimeoutException {
		super();
	}


	@Override
	protected MessageType getMessageType() {
		return MessageType.RESET;
	}

	

    
}
