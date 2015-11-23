package kr.javacafe.core.manager.queue;

import org.junit.Test;

import kr.or.javacafe.core.manager.queue.env.MessageType;
import kr.or.javacafe.core.manager.queue.env.QueueInfo;
import kr.or.javacafe.core.manager.queue.receiver.ExchangeMesasgeListener;
import kr.or.javacafe.core.manager.queue.receiver.MessageHandler;

public class ExchangeConsumerTest {

	//@Test
	public void recv() {
		
		MessageHandler handler = new MessageHandler() {			
			public void onMessage(MessageType messageType, Object data) {
				System.out.println("메세지가 도착하면 할 일을 정의합니다.");
			}
		};
		
		ExchangeMesasgeListener listener;
		try {
			listener = new ExchangeMesasgeListener(QueueInfo.SERVICE_USER_QUEUE_NAME, handler);
			listener.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("큐 Listen 시작...");
	}
}
