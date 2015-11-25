package kr.or.javacafe.bingo.app.apiClient;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kr.or.javacafe.bingo.manager.MemoryManager;
import kr.or.javacafe.core.manager.memoryMap.user.UserVO;

@RestController
@RequestMapping(value = "/api/bingo")
public class ApiClientController {

	private Logger logger = LoggerFactory.getLogger(ApiClientController.class);
	
	
	@Autowired
	private MemoryManager memoryManager;
	

	
	
	
	/**
	 * UUID에 해당하는 사용자 정보 조회
	 * @return
	 */
	@RequestMapping(value = "/user/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<UserVO> user(@PathVariable String uuid) {
		UserVO user = memoryManager.findUser(uuid);
		return new ResponseEntity<UserVO>(user, HttpStatus.OK);
	}

	
	/**
	 * 전체 사용자 정보 조회
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> userList() {
		List<UserVO> users = memoryManager.findAll();
		return new ResponseEntity<List<UserVO>>(users, HttpStatus.OK);
	}
	
	
	/**
	 * [Api Proxy] 활성화 된 게임 리스트 조회
	 * @return
	 */
	@RequestMapping(value = "/gameList", method = RequestMethod.GET)
	public ResponseEntity<?> gameList() {
		try {
			RestTemplate client = new RestTemplate();
			List<GameVO> result = client.getForObject("http://localhost:9601/api/game", List.class);
			
			return new ResponseEntity<List<GameVO>>(result, HttpStatus.OK);
			
		} catch (Exception ex) {
			logger.error("API 호출에 실패하였습니다. " + "[활성화 된 게임 리스트 조회] " + ex.getMessage());
			//ex.printStackTrace();
		}

		return new ResponseEntity<List<GameVO>>(new ArrayList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	/**
	 * [Api Proxy] 게임데이터 리스트 조회
	 * @param gameId
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/gameData/gameId/{gameId}/uuid/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<?> gameData(@PathVariable Long gameId, @PathVariable String uuid) {
		try {
			RestTemplate client = new RestTemplate();
			List<Object> result = client.getForObject("http://localhost:9601/api/game/" + gameId + "/uuid/" + uuid, List.class);
			
			return new ResponseEntity<List<Object>>(result, HttpStatus.OK);
			
		} catch (Exception ex) {
			logger.error("API 호출에 실패하였습니다. " + "[게임데이터 리스트 조회] " + ex.getMessage());
			//ex.printStackTrace();
		}

		return new ResponseEntity<List<GameVO>>(new ArrayList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	/**
	 * [Api Proxy] 게임에 선택된 숫자 리스트 조회
	 * @param gameId
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/gameCheckNumber/gameId/{gameId}", method = RequestMethod.GET)
	public ResponseEntity<?> gameCheckNumber(@PathVariable Long gameId) {
		try {
			RestTemplate client = new RestTemplate();
			List<Object> result = client.getForObject("http://localhost:9601/api/game/" + gameId + "/checkList", List.class);
			
			return new ResponseEntity<List<Object>>(result, HttpStatus.OK);
			
		} catch (Exception ex) {
			logger.error("API 호출에 실패하였습니다. " + "[게임에 선택된 숫자 리스트 조회] " + ex.getMessage());
			//ex.printStackTrace();
		}

		return new ResponseEntity<List<Object>>(new ArrayList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
	/**
	 * [Api Proxy] 사용자별 게임에 Clear 된 라인 카운트 조회
	 * @param gameId
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/gameClearLineCount/gameId/{gameId}/uuid/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<RankingVO> gameClearLineCount(@PathVariable Long gameId, @PathVariable String uuid) {
		try {
			RestTemplate client = new RestTemplate();
			RankingVO result = client.getForObject("http://localhost:9701/api/ranking/game/" + gameId + "/uuid/" + uuid, RankingVO.class);
			
			return new ResponseEntity<RankingVO>(result, HttpStatus.OK);
			
		} catch (Exception ex) {
			logger.error("API 호출에 실패하였습니다. " + "[사용자별 게임에 Clear 된 라인 카운트 조회] " + ex.getMessage());
			//ex.printStackTrace();
		}

		return new ResponseEntity<RankingVO>(new RankingVO(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
}









