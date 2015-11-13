package kr.or.javacafe.core.manager;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import kr.or.javacafe.core.manager.vo.ConfigVO;
import kr.or.javacafe.core.util.JsonUtil;


public class ConfigInfoSender {

	private Logger logger = LoggerFactory.getLogger(ConfigInfoSender.class);

	private final static String QUEUE_NAME = "bingo.api.gateway";
	private final static String QUEUE_SERVER = "localhost";
	private final static String QUEUE_USER = "guest";
	private final static String QUEUE_PW = "guest";
	
	
	private Connection apiServerConnection;
	private Channel apiServerChannel;
	
	
	
	public ConfigInfoSender() throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(QUEUE_SERVER);
	    factory.setUsername(QUEUE_USER);
	    factory.setPassword(QUEUE_PW);
	    
	    apiServerConnection = factory.newConnection();
	    apiServerChannel = apiServerConnection.createChannel();
	}
	
	
	/**
	 * Api Gateway 서버로 메시지를 전송한다.
	 */
	public void sendConfigInfo(String serverName, String serverIp) throws Exception {
		
		ConfigVO vo = new ConfigVO();
		vo.setServerName(serverName);
		vo.setServerIp(serverIp);
		
		String msg = JsonUtil.toJSON(vo);
	    
		apiServerChannel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    apiServerChannel.basicPublish("", QUEUE_NAME, null, msg.getBytes("UTF-8"));
	    
	    logger.info(" [Config 정보 전송] '" + msg + "'");
	}
	


    
}
