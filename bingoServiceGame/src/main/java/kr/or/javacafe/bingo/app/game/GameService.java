package kr.or.javacafe.bingo.app.game;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.javacafe.bingo.app.number.GameData;
import kr.or.javacafe.bingo.app.number.GameDataService;
import kr.or.javacafe.bingo.app.numberCheck.GameCheck;
import kr.or.javacafe.bingo.app.numberCheck.GameCheckService;

@Service
public class GameService {

	@Autowired
	private GameDataService gameDataService;
	
	@Autowired
	private GameCheckService gameCheckService;
	
	@Autowired
	private GameRepository repository;
	
	

	
	@Transactional
	public Game createGame() {
		Game game =  repository.save(new Game());
		gameDataService.createGameDataForAllUser(game);
		
		return game;
	}
	
	
	@Transactional
	public GameCheck createGameCheck(Long id, int checkNumber) {
		return gameCheckService.create(repository.findOne(id), checkNumber);
	}

	
	public List<Game> findGameAll() {
		return repository.findAll();
	}
	


	public List<GameData> findGameByUUID(Long id, String uuid) {
		return gameDataService.findGameData(repository.findOne(id), uuid);
	}
	

	
	public List<GameCheck> findGameCheckList(Long id) {
		return gameCheckService.findGameCheckList(repository.findOne(id));
	}

	
	
	
	
	
}










