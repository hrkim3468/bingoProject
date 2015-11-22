package kr.javacafe.core.manager.queue;

import org.junit.Test;
import kr.or.javacafe.core.manager.queue.receiver.MesasgeListener;
import kr.or.javacafe.core.manager.queue.receiver.MessageHandler;

public class ConsumerTest {

	@Test
	public void recv() {
		MessageHandler handler = new MessageHandler() {
			
			public void onMessage() {
				// TODO Auto-generated method stub
				System.out.println("메세지가 도착하면 할 일을 정의합니다.");
			}
		};
		
		MesasgeListener listener = new MesasgeListener(handler);
		listener.start();
	}
}
