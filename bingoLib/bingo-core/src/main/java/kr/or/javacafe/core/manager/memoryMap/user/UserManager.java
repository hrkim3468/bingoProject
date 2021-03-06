package kr.or.javacafe.core.manager.memoryMap.user;

import java.util.ArrayList;
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

	
	private HashMap<String, UserVO> userMemory = new HashMap<String, UserVO>();
	private String URL;
	
	
	public UserManager(String URL) {
		this.URL = URL;
	}
	
	
	/**
	 * 메모리 생성 작업을 시작한다.
	 */
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
			
			userMemory.put(vo.getUuid(), vo);
		}

		logger.info("[메모리캐시] 사용자 정보 생성 " + userMemory.size());
	}
	
	
	
	/**
	 * 요청한 사용자 정보를 제공한다. 
	 */
	public UserVO getUser(String uuid) {
		return userMemory.get(uuid);
	}
	
	
	
	/**
	 * 전체 사용자 정보를 제공한다.
	 */
	public List<UserVO> getUsers() {
		return new ArrayList<UserVO>(userMemory.values());
	}
	
	
	
	
	
}






