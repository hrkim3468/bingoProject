package kr.or.javacafe.bingo.app.adminApiClient;

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

import kr.or.javacafe.bingo.app.apiClient.GameCheckVO;
import kr.or.javacafe.bingo.app.apiClient.GameVO;
import kr.or.javacafe.bingo.config.webSocket.PushCodeType;
import kr.or.javacafe.bingo.config.webSocket.PushHandler;
import kr.or.javacafe.bingo.manager.MemoryManager;
import kr.or.javacafe.core.manager.memoryMap.user.UserVO;

@RestController
@RequestMapping(value = "/api/bingo/admin/")
public class AdminApiClientController {

	private Logger logger = LoggerFactory.getLogger(AdminApiClientController.class);
	
	
	@Autowired
	private MemoryManager memoryManager;
	
	@Autowired
	private PushHandler pushHandler;
	
	

	
	
	/**
	 * [Api Proxy] 게임 생성
	 * @return
	 */
	@RequestMapping(value = "/createGame", method = RequestMethod.GET)
	public ResponseEntity<GameVO> createGame() {
		try {
			RestTemplate client = new RestTemplate();
			GameVO result = client.postForObject("http://localhost:9601/api/game/createGame", null, GameVO.class);
			
			return new ResponseEntity<GameVO>(result, HttpStatus.CREATED);
			
		} catch (Exception ex) {
			logger.error("API 호출에 실패하였습니다. " + "[게임생성] " + ex.getMessage());
			//ex.printStackTrace();
		}

		return new ResponseEntity<GameVO>(new GameVO(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
	/**
	 * [Api Proxy] 게임 숫자 체크 처리
	 * @param id
	 * @param checkNumber
	 * @return
	 */
	@RequestMapping(value = "/createGameCheck/{id}/check/{checkNumber}", method = RequestMethod.GET)
	public ResponseEntity<GameCheckVO> createGameCheck(@PathVariable Long id, @PathVariable int checkNumber) {
		try {
			RestTemplate client = new RestTemplate();
			GameCheckVO result = client.postForObject("http://localhost:9601/api/game/createGameCheck/" + id + "/check/" + checkNumber, null, GameCheckVO.class);
			
			// 요청이 정상적으로 완료되면 결과를 Push한다.
			List<UserVO> users = memoryManager.findAll();
			for (UserVO user : users) {
				pushHandler.sendMessage(PushCodeType.CHECK_NUMBER, result.getGame().getId(), result.getCheckNumber(), user.getUuid());
			}
			
			return new ResponseEntity<GameCheckVO>(result, HttpStatus.CREATED);
			
		} catch (Exception ex) {
			logger.error("API 호출에 실패하였습니다. " + "[게임 숫자 체크 처리] " + ex.getMessage());
			//ex.printStackTrace();
		}

		return new ResponseEntity<GameCheckVO>(new GameCheckVO(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	
	
}









