package kr.or.javacafe.bingo.app.number;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.javacafe.bingo.app.common.RandomUtil;
import kr.or.javacafe.bingo.app.game.Game;
import kr.or.javacafe.bingo.manager.MemoryManager;
import kr.or.javacafe.core.manager.memoryMap.user.UserVO;

@Service
public class GameDataService {

	@Autowired
	private MemoryManager memoryManager;
	
	@Autowired
	private GameDataRepository repository;
	
	

	public List<GameData> findGameData(Game game, String uuid) {
		return repository.findByGameAndUuid(game, uuid);
	}
		
	
	@Transactional
	public void createGameDataForAllUser(Game game) {
		List<UserVO> users = memoryManager.findAll();
		for (UserVO user : users) {
			
			int[] randomNumber = RandomUtil.shuffle(RandomUtil.getBaseArray(25));
			
			for (int i=0; i<25; i++) {
				GameData data = new GameData();
				data.setGame(game);
				data.setUuid(user.getUuid());
				data.setDataIndex(i+1);
				data.setDataNumber(randomNumber[i]);
				
				repository.save(data);
			}			
		}
	}

	
	

}
