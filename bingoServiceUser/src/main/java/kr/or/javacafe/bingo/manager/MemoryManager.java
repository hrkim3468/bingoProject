package kr.or.javacafe.bingo.manager;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import kr.or.javacafe.core.manager.memoryMap.user.UserManager;

@Component
public class MemoryManager {

	private Logger logger = LoggerFactory.getLogger(QueueManager.class);
	
	private UserManager userManager;
	
	
	
	@PostConstruct
	public void init() {
		userManager = new UserManager("http://localhost:9501/api/users");
		userManager.start();
	}
	
	
	public void userManagerReset() {
		userManager.start();
	}
	
}
