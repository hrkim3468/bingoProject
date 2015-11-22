package kr.javacafe.core.manager.queue;

import org.junit.Test;

import kr.or.javacafe.core.manager.queue.sender.ConfigMessageSender;

public class ProducerTest {

	@Test
	public void send() throws Exception {
		ConfigMessageSender sender = new ConfigMessageSender();
		sender.send("testServer", "apiTest", "localhost");
		sender.release();
	}
	
}
