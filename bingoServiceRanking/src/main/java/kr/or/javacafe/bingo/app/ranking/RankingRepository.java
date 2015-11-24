package kr.or.javacafe.bingo.app.ranking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

	public List<Ranking> findByGameId(Long gameId);
	public Ranking findByGameIdAndUuid(Long gameId, String uuid);
	
}
