package kr.or.javacafe.core.spring.component.test;


import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import kr.or.javacafe.core.spring.prop.SystemProperty;



@Component
public class Queue2Test {

	private Logger logger = LoggerFactory.getLogger(Queue2Test.class);
	
	@Autowired
	private SystemProperty systemPro;

	private final static String QUEUE_NAME = "bingo.api.gateway";
	private final static String QUEUE_SERVER = "localhost";
	private final static String QUEUE_USER = "guest";
	private final static String QUEUE_PW = "guest";
	
	
	private Connection apiServerConnection;
	private Channel apiServerChannel;
	
	//@PostConstruct
	public void init() {
		try {
			initApiGatewaySender();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	


	/**
	 * Api Gateway 서버로 메시지를 전송하기 위한 Sender 초기화 
	 * 
	 * @throws Exception
	 */
	public void initApiGatewaySender() throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(QUEUE_SERVER);
	    factory.setUsername(QUEUE_USER);
	    factory.setPassword(QUEUE_PW);
	    
	    apiServerConnection = factory.newConnection();
	    apiServerChannel = apiServerConnection.createChannel();
	}
	
	
	/**
	 * Api Gateway 서버로 메시지를 전송한다.
	 * 
	 * @param msg
	 * @throws Exception
	 */
	public void sendConfigInfo(String msg) throws Exception {
	    apiServerChannel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    apiServerChannel.basicPublish("", QUEUE_NAME, null, msg.getBytes("UTF-8"));
	    
	    logger.info(" [메시지 전송] '" + msg + "'");
	}
	
	
	
	public void send(String msg) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(QUEUE_SERVER);
	    factory.setUsername(QUEUE_USER);
	    factory.setPassword(QUEUE_PW);
	    
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    channel.basicPublish("", QUEUE_NAME, null, msg.getBytes("UTF-8"));
	    
	    System.out.println(" [x] Sent '" + msg + "'");

	    channel.close();
	    connection.close();		
	}
	

	public void recv()  throws Exception {
		
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(QUEUE_SERVER);
	    factory.setUsername(QUEUE_USER);
	    factory.setPassword(QUEUE_PW);
	    
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
	    };
	    channel.basicConsume(QUEUE_NAME, true, consumer);		
	}
    
}
