package kr.or.javacafe.bingo.manager;

import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.javacafe.core.manager.memoryMap.user.UserManager;
import kr.or.javacafe.core.manager.queue.env.MessageType;
import kr.or.javacafe.core.manager.queue.env.QueueInfo;
import kr.or.javacafe.core.manager.queue.message.ConfigMessage;
import kr.or.javacafe.core.manager.queue.message.ResetMessage;
import kr.or.javacafe.core.manager.queue.receiver.ExchangeMesasgeListener;
import kr.or.javacafe.core.manager.queue.receiver.MessageHandler;
import kr.or.javacafe.core.manager.queue.receiver.QueueMesasgeListener;
import kr.or.javacafe.core.manager.queue.sender.ConfigMessageSender;
import kr.or.javacafe.core.manager.queue.sender.ResetMessageSender;
import kr.or.javacafe.core.spring.prop.SystemProperty;

@Component
public class QueueManager {

	private Logger logger = LoggerFactory.getLogger(QueueManager.class);

	private ConfigMessageSender configSender;
	private ResetMessageSender resetSender;
	
	private QueueMesasgeListener listener;
	private ExchangeMesasgeListener exListener;
	
	
	@Autowired
	private SystemProperty systemProp;

	@Autowired
	private ServerConfigManager serverConfigManager;
	
	@Autowired
	private MemoryManager memoryManager;
	
	
	/**
	 * ApiGateway 서버 기동시
	 *  
	 * - 서버 설정정보를 전송한다. 
	 * - 서버 설정정보 큐를 Listen 한다.
	 * - BroadCast 큐를 Listen 한다.
	 */
	@PostConstruct
	public void init() {
		try {		
			configSender = new ConfigMessageSender();
			resetSender = new ResetMessageSender();
			
			// 설정정보 전송
			serverConfigSend();
			
			// 설정정보 큐 리슨
			serverConfigReceive();
			
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
			configSender.release();
			resetSender.release();
			
			listener.release();
			exListener.release();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 서버 설정정보 메시지 전송 함수
	 */
	public void serverConfigSend() {
		try {		
			ConfigMessage message = new ConfigMessage();
			message.setServerType(systemProp.getType());
			message.setServerName(systemProp.getName());
			message.setServerIp(systemProp.getIp());
			
			configSender.send(message);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}		
	}
	

	
	
	/**
	 * 서버 설정정보 메세지 콜백 함수
	 */
	public void serverConfigReceive() {
		try {
			MessageHandler handler = new MessageHandler() {			
				public void onMessage(MessageType messageType, Object data) {
					logger.info("============> QueueMesasgeListener 메세지 타입 : " + messageType);
					@SuppressWarnings({ "unchecked", "unused" })
					LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) data;
					
					if (messageType.SERVER_CONFIG.equals(messageType)) {	
						ConfigMessage vo = new ConfigMessage();
						vo.setServerType((String)map.get("serverType"));
						vo.setServerName((String)map.get("serverName"));
						vo.setServerIp((String)map.get("serverIp"));
						
						serverConfigManager.addServerConfig(vo);
						return;
					}
					
					logger.error("처리가 불가능한 메세지가 도착했습니다. : " + messageType);
					return;
				}
			};
			
			listener = new QueueMesasgeListener(QueueInfo.GATEWAY_QUEUE_NAME, handler);
			listener.start();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 서버 리셋 메시지 전송 함수
	 */
	public void serverResetSend() {
		try {		
			ResetMessage message = new ResetMessage();
			message.setDesc("관리자가 API Gateway를 통하여 리셋을 요청했습니다.");
			
			resetSender.send(message);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	
	
	/**
	 * BroadCast 메세지 콜백 함수
	 */
	public void boardcastReceive() {
		try {
			MessageHandler handler = new MessageHandler() {			
				public void onMessage(MessageType messageType, Object data) {
					logger.info("============> ExchangeMesasgeListener 메세지 타입 : " + messageType);
					@SuppressWarnings({ "unchecked", "unused" })
					LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) data;
					
					if (messageType.RESET.equals(messageType)) {
						memoryManager.userManagerReset();
						return;
					}
					
					logger.error("처리가 불가능한 메세지가 도착했습니다. : " + messageType);
					return;
				}
			};
			
			exListener = new ExchangeMesasgeListener(QueueInfo.EXCHANGE_GATEWAY_QUEUE_NAME, handler);
			exListener.start();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	
}




