package kr.javacafe.core.manager.queue;

import org.junit.Test;

import kr.or.javacafe.core.manager.queue.message.ConfigMessage;
import kr.or.javacafe.core.manager.queue.message.ResetMessage;
import kr.or.javacafe.core.manager.queue.sender.ConfigMessageSender;
import kr.or.javacafe.core.manager.queue.sender.ResetMessageSender;

public class ProducerTest {

	//@Test
	public void sendConfigMessage() throws Exception {
		
		ConfigMessage message = new ConfigMessage();
		message.setServerType("testServer");
		message.setServerName("apiTest");
		message.setServerIp("localhost");
		
		ConfigMessageSender sender = new ConfigMessageSender();
		sender.send(message);
		sender.release();
	}
	
	
	//@Test
	public void sendResetMessage() throws Exception {
		
		ResetMessage message = new ResetMessage();
		message.setDesc("정보를 리셋합니다.");
		
		ResetMessageSender sender = new ResetMessageSender();
		sender.send(message);
		sender.release();
	}
}
