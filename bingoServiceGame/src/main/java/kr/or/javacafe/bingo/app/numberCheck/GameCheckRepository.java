package kr.or.javacafe.bingo.app.numberCheck;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.javacafe.bingo.app.game.Game;

public interface GameCheckRepository extends JpaRepository<GameCheck, Long> {

	public List<GameCheck> findByGame(Game game);
	
}
