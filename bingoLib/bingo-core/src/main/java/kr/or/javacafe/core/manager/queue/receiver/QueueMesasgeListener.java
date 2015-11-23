package kr.or.javacafe.core.manager.queue.receiver;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import kr.or.javacafe.core.manager.queue.env.QueueInfo;
import kr.or.javacafe.core.util.MessageJsonUtil;
import kr.or.javacafe.core.util.vo.MessageResult;

public class QueueMesasgeListener {

	private Logger logger = LoggerFactory.getLogger(QueueMesasgeListener.class);
	
	
	private MessageHandler cb;
	private String queueName;
	
	private Connection connection;
	private Channel channel;

	

	/**
	 * 클래스 생성시 AMQP 서버로 접속한다.
	 */
	public QueueMesasgeListener(String queueName, MessageHandler cb) throws IOException, TimeoutException {
		
		this.cb = cb;
		this.queueName = queueName;
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(QueueInfo.QUEUE_SERVER);
	    factory.setUsername(QueueInfo.QUEUE_USER);
	    factory.setPassword(QueueInfo.QUEUE_PW);
	    
	    connection = factory.newConnection();
	    channel = connection.createChannel();
	}

	
	

	/**
	 * Listen 상태로 설정된 큐에 메세지가 도착하면 사용자쪽 메소드를 Callback 해준다. (ASync)
	 */
	public void start() throws IOException {

		channel.queueDeclare(queueName, false, false, false, null);		
		logger.info(" [QUEUE 수신을 위해 Listen] " + queueName);
		
	    Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");
				logger.info(" [QUEUE 수신 메시지] '" + msg + "'");
				
				// 메세지가 도착하면 사용자에게 Callback
				MessageResult obj = MessageJsonUtil.toMessageObj(msg);
				if ("fail".equals(obj.getResult())) {
					logger.error("메세지 패킷 변환에 실패한 메시지가 도착하였습니다.");
				} else {
					cb.onMessage(obj.getMessageType(), obj.getData());
				}
			}
	    };
	    
	    channel.basicConsume(queueName, true, consumer);
	}
	

	
	/**
	 * 클래스 소멸시 큐 자원을 반납한다.
	 */
	public void release() throws Exception {
		channel.close();
		connection.close();
	}
	
}











