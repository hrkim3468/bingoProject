package kr.or.javacafe.core.manager.queue.sender;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import kr.or.javacafe.core.manager.queue.env.MessageType;


public class RankingMessageSender extends MessageSender {

	
	public RankingMessageSender() throws IOException, TimeoutException {
		super();
	}


	@Override
	protected MessageType getMessageType() {
		return MessageType.RANKING;
	}

	

    
}
