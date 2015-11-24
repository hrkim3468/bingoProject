package kr.or.javacafe.bingo.app.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.javacafe.bingo.app.number.GameData;
import kr.or.javacafe.bingo.app.numberCheck.GameCheck;

@RestController
@RequestMapping(value = "/api/game")
public class GameController {

	@Autowired
	private GameService service;
	

	
	/**
	 * 게임 생성 (by 관리자)
	 * @return
	 */
	@RequestMapping(value = "/createGame", method = RequestMethod.POST)
	public ResponseEntity<Game> createGame() {
		Game game = service.createGame();
		return new ResponseEntity<>(game, HttpStatus.CREATED);
	}
	
	
	/**
	 * 게임 숫자 체크 처리 (by 관리자)
	 * @param id
	 * @param checkNumber
	 * @return
	 */
	@RequestMapping(value = "/createGameCheck/{id}/check/{checkNumber}", method = RequestMethod.POST)
	public ResponseEntity<GameCheck> createGameCheck(@PathVariable Long id, @PathVariable int checkNumber) {
		GameCheck gameCheck = service.createGameCheck(id, checkNumber);
		return new ResponseEntity<>(gameCheck, HttpStatus.CREATED);
	}

	
	/**
	 * 생성된 게임 리스트 조회
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Game> games = service.findGameAll();
		
		return new ResponseEntity<>(games, HttpStatus.OK);
	}

	
	/**
	 * 사용자별 게임상세
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/uuid/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<?> findGame(@PathVariable Long id, @PathVariable String uuid) {
		List<GameData> gameDatas = service.findGameByUUID(id, uuid);
		
		return new ResponseEntity<>(gameDatas, HttpStatus.OK);
	}
	
	

	/**
	 * 게임별 숫자 체크리스트
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/checkList", method = RequestMethod.GET)
	public ResponseEntity<?> gameCheckList(@PathVariable Long id) {
		List<GameCheck> gameChecks = service.findGameCheckList(id);
		
		return new ResponseEntity<>(gameChecks, HttpStatus.OK);
	}

	
}
