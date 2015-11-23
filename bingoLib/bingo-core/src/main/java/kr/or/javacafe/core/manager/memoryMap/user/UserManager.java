package kr.or.javacafe.core.manager.memoryMap.user;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import kr.or.javacafe.core.manager.queue.sender.MessageSender;


public class UserManager {

	private Logger logger = LoggerFactory.getLogger(MessageSender.class);

	
	private HashMap<String, UserVO> userMemeory = new HashMap<String, UserVO>();
	private String URL;
	
	
	public UserManager(String URL) {
		this.URL = URL;
	}
	
	
	public void start() {
		RestTemplate client = new RestTemplate();
		ResponseEntity<List> entity;
		
		try {
			entity = client.getForEntity(URL, List.class);			
			
			if (HttpStatus.OK != entity.getStatusCode()) {
				logger.error("사용자 정보 생성에 실패하였습니다.");
				return;
			}

		} catch (Exception ex) {
			logger.error("사용자 정보 생성에 실패하였습니다. " + ex.getMessage());
			//ex.printStackTrace();
			return;
		}

		List<LinkedHashMap<String, Object>> maps = entity.getBody();
		for (LinkedHashMap<String, Object> map : maps) {
			UserVO vo = new UserVO();
			vo.setId(new Long((Integer)map.get("id")));
			vo.setUuid((String)map.get("uuid"));
			vo.setName((String)map.get("name"));
			vo.setEmail((String)map.get("email"));
			vo.setCompany((String)map.get("company"));
			vo.setPhone((String)map.get("phone"));
			
			userMemeory.put(vo.getUuid(), vo);
		}

		logger.info("[메모리캐시] 사용자 정보 생성 " + userMemeory.size());
	}
	
}
