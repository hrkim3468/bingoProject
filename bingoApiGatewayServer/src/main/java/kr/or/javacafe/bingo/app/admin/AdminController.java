package kr.or.javacafe.bingo.app.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.javacafe.bingo.manager.QueueManager;
import kr.or.javacafe.bingo.manager.ServerConfigManager;
import kr.or.javacafe.core.manager.queue.message.ConfigMessage;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
	

	@Autowired
	private QueueManager queueManager;
	
	@Autowired
	private ServerConfigManager serverConfigManager;
	

	
	
	@RequestMapping(value = "/userCacheReset", method = RequestMethod.GET)
	public ResponseEntity<String> userCacheReset() {
		queueManager.serverResetSend();		
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/serverConfigs", method = RequestMethod.GET)
	public ResponseEntity<?> serverConfigs() {
		List<ConfigMessage> result = serverConfigManager.readAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	
	
	
	

}
