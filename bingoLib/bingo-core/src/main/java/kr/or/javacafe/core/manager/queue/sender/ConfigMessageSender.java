package kr.or.javacafe.core.manager.queue.sender;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import kr.or.javacafe.core.manager.queue.env.MessageType;
import kr.or.javacafe.core.manager.queue.env.QueueInfo;
import kr.or.javacafe.core.manager.queue.message.ConfigMessageVO;
import kr.or.javacafe.core.util.JsonUtil;


public class ConfigMessageSender {

	private Logger logger = LoggerFactory.getLogger(ConfigMessageSender.class);

	
	private Connection apiServerConnection;
	private Channel apiServerChannel;
	
	
	
	public ConfigMessageSender() throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(QueueInfo.QUEUE_SERVER);
	    factory.setUsername(QueueInfo.QUEUE_USER);
	    factory.setPassword(QueueInfo.QUEUE_PW);
	    
	    apiServerConnection = factory.newConnection();
	    apiServerChannel = apiServerConnection.createChannel();
	}
	
	
	/**
	 * Api Gateway 서버로 메시지를 전송한다.
	 */
	public void send(String serverType, String serverName, String serverIp) throws Exception {
		
		ConfigMessageVO vo = new ConfigMessageVO();
		vo.setMessageType(MessageType.SERVER_CONFIG);
		vo.setServerType(serverType);
		vo.setServerName(serverName);
		vo.setServerIp(serverIp);
		vo.setMessageCreateTime(new Date());
		
		String msg = JsonUtil.toJSON(vo);
	    
		apiServerChannel.queueDeclare(QueueInfo.GATEWAY_QUEUE_NAME, false, false, false, null);
	    apiServerChannel.basicPublish("", QueueInfo.GATEWAY_QUEUE_NAME, null, msg.getBytes("UTF-8"));
	    
	    logger.info(" [Config 정보 전송] '" + msg + "'");
	}
	
	

	public void release() throws Exception {
		apiServerChannel.close();
		apiServerConnection.close();
	}


    
}
