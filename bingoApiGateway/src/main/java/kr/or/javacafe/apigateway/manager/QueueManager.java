package kr.or.javacafe.apigateway.manager;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.javacafe.apigateway.config.prop.SystemProperty;
import kr.or.javacafe.core.manager.ConfigInfoSender;

@Service
public class QueueManager {

	private Logger logger = LoggerFactory.getLogger(QueueManager.class);

	@Autowired
	private SystemProperty systemPro;
	
	
	
	@PostConstruct
	public void init() {
		try {
			ConfigInfoSender configSender = new ConfigInfoSender();
			configSender.sendConfigInfo(systemPro.getName(), systemPro.getIp());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
