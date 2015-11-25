package kr.or.javacafe.core.manager.queue.sender;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import kr.or.javacafe.core.manager.queue.env.MessageType;


public class RankingChangeMessageSender extends MessageSender {

	
	public RankingChangeMessageSender() throws IOException, TimeoutException {
		super();
	}


	@Override
	protected MessageType getMessageType() {
		return MessageType.RANKING_CHANGE;
	}

	

    
}
