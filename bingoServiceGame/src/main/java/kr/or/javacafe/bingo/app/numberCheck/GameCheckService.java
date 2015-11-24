package kr.or.javacafe.bingo.app.numberCheck;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.javacafe.bingo.app.game.Game;

@Service
public class GameCheckService {
	
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
	
	
}
