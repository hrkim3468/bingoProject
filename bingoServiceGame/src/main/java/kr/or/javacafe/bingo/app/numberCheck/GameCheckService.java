package kr.or.javacafe.bingo.app.numberCheck;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import kr.or.javacafe.bingo.app.common.GameUtil;
import kr.or.javacafe.bingo.app.game.Game;
import kr.or.javacafe.bingo.app.number.GameData;
import kr.or.javacafe.bingo.app.number.GameDataService;
import kr.or.javacafe.bingo.manager.MemoryManager;
import kr.or.javacafe.bingo.manager.QueueManager;
import kr.or.javacafe.core.manager.memoryMap.user.UserVO;

@EnableAsync
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
	public GameCheck createGameCheck(Game game, int checkNumber) {
		GameCheck gameCheck = new GameCheck();
		gameCheck.setGame(game);
		gameCheck.setCheckNumber(checkNumber);
		
		return repository.save(gameCheck);
	}
	
	
	@Async
	public void aSyncRankingCheckForAllUser(Game game) {
		List<UserVO> users = memoryManager.findAll();
		for (UserVO user : users) {
			List<GameData> gameDatas = gameDataService.findGameData(game, user.getUuid());
			List<GameCheck> gameChecks = findGameCheckList(game);
			
			// 사용자별로 Clear 카운트 수를 계산한다.
			int clearLineCount = GameUtil.lineClearCheck(gameDatas, gameChecks);
			
			// 사용자별로 생성된 Clear 카운트를 Ranking 서버로 보내서 DB에 저장한다.
			logger.debug("사용자별로 계산된 Ranking 정보를 Ranking 큐로 전송합니다.");
			queueManager.rankingSend(game.getId(), user.getUuid(), clearLineCount);
		}
		
		// 모든 사용자 정보를 보내는것이 완료되면 전체 시스템에 Ranking Change 메시지를 보내서 Ranking 데이터를 갱신하도록 한다.
		queueManager.rankingChangeSend(game.getId());
	}




}



