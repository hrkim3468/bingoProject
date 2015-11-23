package kr.or.javacafe.core.manager.queue.receiver;

import kr.or.javacafe.core.manager.queue.env.MessageType;

public interface MessageHandler {
	public void onMessage(MessageType messageType, Object data);
}
