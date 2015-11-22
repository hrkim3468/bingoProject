package kr.or.javacafe.apigateway.manager;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.javacafe.apigateway.config.prop.SystemProperty;
import kr.or.javacafe.core.manager.ConfigInfoSender;
import kr.or.javacafe.core.spring.service.TestService;

@Service
public class QueueManager {

	private Logger logger = LoggerFactory.getLogger(QueueManager.class);

	@Autowired
	private SystemProperty systemProp;
	
	@Autowired
	private TestService testService;
	
	
	
	/**
	 * 서버 기동시 IP 정보를 큐로 전송한다. 
	 */
	@PostConstruct
	public void init() {
		try {
			logger.info("CORE 호출 결과 : " + testService.getHello());
			
			ConfigInfoSender configSender = new ConfigInfoSender();
			configSender.sendConfigInfo(systemProp.getType(), systemProp.getName(), systemProp.getIp());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
