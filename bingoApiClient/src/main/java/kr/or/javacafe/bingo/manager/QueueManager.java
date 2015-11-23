package kr.or.javacafe.bingo.manager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.javacafe.core.manager.queue.env.MessageType;
import kr.or.javacafe.core.manager.queue.env.QueueInfo;
import kr.or.javacafe.core.manager.queue.message.ConfigMessage;
import kr.or.javacafe.core.manager.queue.receiver.ExchangeMesasgeListener;
import kr.or.javacafe.core.manager.queue.receiver.MessageHandler;
import kr.or.javacafe.core.manager.queue.sender.ConfigMessageSender;
import kr.or.javacafe.core.spring.prop.SystemProperty;

@Service
public class QueueManager {

	private Logger logger = LoggerFactory.getLogger(QueueManager.class);

	ConfigMessageSender sender;
	ExchangeMesasgeListener exListener;
	
	
	@Autowired
	private SystemProperty systemProp;

	
	
	/**
	 * apiClient 서버 기동시 
	 * 
	 * - 서버의 설정정보를 전송한다. 
	 * - BroadCast 큐를 Listen 한다.
	 */
	@PostConstruct
	public void init() {
		try {
			// 설정정보 전송
			sender = new ConfigMessageSender();
			
			ConfigMessage message = new ConfigMessage();
			message.setServerType(systemProp.getType());
			message.setServerName(systemProp.getName());
			message.setServerIp(systemProp.getIp());
			
			sender.send(message);
			
			// BroadCast 큐 리슨
			boardcastReceive();

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 서버 종료시 큐 접속 자원을 소멸시킨다.
	 */
	@PreDestroy
	public void destroy() {
		try {
			sender.release();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * BroadCast 메세지 도착시 콜백 처리
	 */
	public void boardcastReceive() {
		try {
			MessageHandler handler = new MessageHandler() {			
				public void onMessage(MessageType messageType, Object data) {
					logger.info("============> 메세지 타입 : " + messageType);
					
					if (messageType.RESET.equals(messageType)) {					
						return;
					}
					
					logger.error("처리가 불가능한 메세지가 도착했습니다. : " + messageType);
					return;
				}
			};
			
			exListener = new ExchangeMesasgeListener(QueueInfo.EXCHANGE_SERVICE_USER_QUEUE_NAME, handler);
			exListener.start();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
}
