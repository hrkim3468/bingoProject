package kr.or.javacafe.bingo.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import kr.or.javacafe.core.manager.queue.message.ConfigMessage;

@Component
public class ServerConfigManager {

	private Logger logger = LoggerFactory.getLogger(ServerConfigManager.class);
	
	private HashMap<String, ConfigMessage> memory = new HashMap<String, ConfigMessage>();
	
	
	
	public void addServerConfig(ConfigMessage message) {
		memory.put(message.getServerName(), message);
	}
	
	
	public List<ConfigMessage> readAll() {
		return new ArrayList<ConfigMessage>(memory.values());		
	}
	
	
}
