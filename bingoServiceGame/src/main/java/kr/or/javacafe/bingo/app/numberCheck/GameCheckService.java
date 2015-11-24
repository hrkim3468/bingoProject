package kr.or.javacafe.bingo.app.numberCheck;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import kr.or.javacafe.bingo.app.common.GameUtil;
import kr.or.javacafe.bingo.app.game.Game;
import kr.or.javacafe.bingo.app.number.GameData;
import kr.or.javacafe.bingo.app.number.GameDataService;
import kr.or.javacafe.bingo.manager.MemoryManager;
import kr.or.javacafe.bingo.manager.QueueManager;
import kr.or.javacafe.core.manager.memoryMap.user.UserVO;

@Service
public class GameCheckService {
	
	private Logger logger = LoggerFactory.getLogger(GameCheckService.class);
	
	
	@Autowired
	private MemoryManager memoryManager;
	
	@Autowired
	private QueueManager queueManager;
	
	@Autowired
	private GameDataService gameDataService;
	
	@Autowired
	private GameCheckRepository repository;
	

	
	public List<GameCheck> findGameCheckList(Game game) {
		return repository.findByGame(game);
	}
	
	
	@Transactional
	public GameCheck create(Game game, int checkNumber) {
		GameCheck gameCheck = new GameCheck();
		gameCheck.setGame(game);
		gameCheck.setCheckNumber(checkNumber);
		
		return repository.save(gameCheck);
	}
	
	
	@Async
	public void aSyncRankingCheck(Game game) {
		List<UserVO> users = memoryManager.findAll();
		for (UserVO user : users) {
			List<GameData> gameDatas = gameDataService.findGameData(game, user.getUuid());
			List<GameCheck> gameChecks = findGameCheckList(game);
			
			int clearLineCount = GameUtil.lineClearCheck(gameDatas, gameChecks);
			
			// 사용자별로 데이터를 계산하여 생성된 Clear 카운트를 Ranking 서버로 전송한다.
			logger.debug("사용자별로 계산된 Ranking 정보를 전송합니다.");
			queueManager.rankingSend(game.getId(), user.getUuid(), clearLineCount);
		}
	}




}



