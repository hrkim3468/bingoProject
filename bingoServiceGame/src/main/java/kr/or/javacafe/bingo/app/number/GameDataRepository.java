package kr.or.javacafe.bingo.app.number;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.javacafe.bingo.app.game.Game;

public interface GameDataRepository extends JpaRepository<GameData, Long> {

	public List<GameData> findByGameAndUuid(Game game, String uuid);
	
}
