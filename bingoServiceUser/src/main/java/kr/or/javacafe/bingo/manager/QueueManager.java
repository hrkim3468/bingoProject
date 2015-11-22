package kr.or.javacafe.bingo.manager;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.javacafe.core.manager.queue.sender.ConfigMessageSender;
import kr.or.javacafe.core.spring.prop.SystemProperty;

@Service
public class QueueManager {

	private Logger logger = LoggerFactory.getLogger(QueueManager.class);

	@Autowired
	private SystemProperty systemProp;

	
	
	/**
	 * 서버 기동시 서버 정보를 큐로 전송한다. 
	 */
	@PostConstruct
	public void init() {
		try {
			logger.info("============> 서버 구동시 서버정보 큐로 전송...");			
			ConfigMessageSender sender = new ConfigMessageSender();
			sender.send(systemProp.getType(), systemProp.getName(), systemProp.getIp());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
