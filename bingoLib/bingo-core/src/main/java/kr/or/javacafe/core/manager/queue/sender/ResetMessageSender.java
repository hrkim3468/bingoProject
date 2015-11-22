package kr.or.javacafe.core.manager.queue.sender;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import kr.or.javacafe.core.manager.queue.env.MessageType;
import kr.or.javacafe.core.manager.queue.env.QueueInfo;
import kr.or.javacafe.core.manager.queue.message.ResetMessageVO;
import kr.or.javacafe.core.util.JsonUtil;


public class ResetMessageSender {

	private Logger logger = LoggerFactory.getLogger(ResetMessageSender.class);

	
	private Connection apiServerConnection;
	private Channel apiServerChannel;
	
	
	
	public ResetMessageSender() throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(QueueInfo.QUEUE_SERVER);
	    factory.setUsername(QueueInfo.QUEUE_USER);
	    factory.setPassword(QueueInfo.QUEUE_PW);
	    
	    apiServerConnection = factory.newConnection();
	    apiServerChannel = apiServerConnection.createChannel();
	}
	
	
	/**
	 * Exchange로 메시지를 전송하여 모든 서버가 Broadcasting 되도록 한다.
	 */
	public void send(String desc) throws Exception {
		
		ResetMessageVO vo = new ResetMessageVO();
		vo.setMessageType(MessageType.RESET);
		vo.setDesc(desc);
		vo.setMessageCreateTime(new Date());
		
		String msg = JsonUtil.toJSON(vo);
	    
		apiServerChannel.exchangeDeclare(QueueInfo.EXCHANGE_NAME, "fanout");
		apiServerChannel.basicPublish(QueueInfo.EXCHANGE_NAME, "", null, msg.getBytes("UTF-8"));
	    
	    logger.info(" [Reset 정보 전송] '" + msg + "'");
	}
	
	

	public void release() throws Exception {
		apiServerChannel.close();
		apiServerConnection.close();
	}


    
}
