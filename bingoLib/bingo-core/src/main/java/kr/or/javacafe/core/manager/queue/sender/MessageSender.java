package kr.or.javacafe.core.manager.queue.sender;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import kr.or.javacafe.core.manager.queue.env.MessageType;
import kr.or.javacafe.core.manager.queue.env.QueueInfo;
import kr.or.javacafe.core.manager.queue.message.ConfigMessage;
import kr.or.javacafe.core.manager.queue.message.Message;
import kr.or.javacafe.core.manager.queue.message.RankingChangeMessage;
import kr.or.javacafe.core.manager.queue.message.RankingMessage;
import kr.or.javacafe.core.manager.queue.message.ResetMessage;
import kr.or.javacafe.core.util.MessageJsonUtil;


public abstract class MessageSender {

	private Logger logger = LoggerFactory.getLogger(MessageSender.class);

	
	private Connection connecetion;
	private Channel channel;
	
	
	
	/**
	 * 클래스 생성시 AMQP 서버로 접속한다.
	 */
	public MessageSender() throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(QueueInfo.QUEUE_SERVER);
	    factory.setUsername(QueueInfo.QUEUE_USER);
	    factory.setPassword(QueueInfo.QUEUE_PW);
	    
	    connecetion = factory.newConnection();
	    channel = connecetion.createChannel();
	}
	
	
	
	
	/**
	 * MessageSender를 상속받는 구현체는 MessageType 정보를 리턴하는 메소드를 반드시 구현해야 한다.
	 */
	protected abstract MessageType getMessageType();
	
	
	

	/**
	 * 사용자 요청시 메세지타입을 검사하여 메세지를 전송한다. (Sync)
	 */
	public void send(Message message) throws IOException {
		String result = "";
		
		if (MessageType.SERVER_CONFIG.equals(this.getMessageType())) {
			
			// 서버정보 메시지 도착시 ApiGateway 서버로 메시지를 전송한다.
			result = MessageJsonUtil.toMessageJSON(this.getMessageType(), (ConfigMessage)message);
			
			channel.queueDeclare(QueueInfo.GATEWAY_QUEUE_NAME, false, false, false, null);
		    channel.basicPublish("", QueueInfo.GATEWAY_QUEUE_NAME, null, result.getBytes("UTF-8"));
		
		} else if (MessageType.RANKING.equals(this.getMessageType())) {
			
			// Ranking 정보 메시지 도착시 BingServiceRanking 서버로 메시지를 전송한다.
			result = MessageJsonUtil.toMessageJSON(this.getMessageType(), (RankingMessage)message);
			
			channel.queueDeclare(QueueInfo.SERVICE_RANKING_QUEUE_NAME, false, false, false, null);
		    channel.basicPublish("", QueueInfo.SERVICE_RANKING_QUEUE_NAME, null, result.getBytes("UTF-8"));
		    
		} else if (MessageType.RANKING_CHANGE.equals(this.getMessageType())) {
			
			// Ranking 변경 메시지 도착시 모든 서버로 메세지를 브로드캐스팅한다.
			result = MessageJsonUtil.toMessageJSON(this.getMessageType(), (RankingChangeMessage)message);
			
			channel.exchangeDeclare(QueueInfo.EXCHANGE_NAME, "fanout");
		    channel.basicPublish(QueueInfo.EXCHANGE_NAME, "", null, result.getBytes("UTF-8"));
		
		} else if (MessageType.RESET.equals(this.getMessageType())) {
			
			// 리셋 메시지 도착시 모든 서버로 메세지를 브로드캐스팅한다.
			result = MessageJsonUtil.toMessageJSON(this.getMessageType(), (ResetMessage)message);
			
			channel.exchangeDeclare(QueueInfo.EXCHANGE_NAME, "fanout");
		    channel.basicPublish(QueueInfo.EXCHANGE_NAME, "", null, result.getBytes("UTF-8"));
		
		} else {
			logger.error("지원하지 않는 형태의 메세지가 유입되었습니다.");
			return;
		}
	    
	    logger.info(" [QUEUE 전송 메세지] '" + result + "'");
	}
	
	
	
	
	/**
	 * 클래스 소멸시 큐 자원을 반납한다.
	 */
	public void release() throws Exception {
		channel.close();
		connecetion.close();
	}


	
	
    
}
